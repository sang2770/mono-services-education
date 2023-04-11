package com.sang.nv.education.exam.application.service.impl;


import com.sang.commonmodel.dto.PageDTO;
import com.sang.commonmodel.exception.ResponseException;
import com.sang.commonmodel.mapper.util.PageableMapperUtil;
import com.sang.commonweb.support.SecurityUtils;
import com.sang.nv.education.exam.application.dto.request.UserExamCreateRequest;
import com.sang.nv.education.exam.application.dto.request.UserRoomSearchRequest;
import com.sang.nv.education.exam.application.dto.response.UserExamResult;
import com.sang.nv.education.exam.application.mapper.ExamAutoMapper;
import com.sang.nv.education.exam.application.service.UserExamService;
import com.sang.nv.education.exam.domain.Exam;
import com.sang.nv.education.exam.domain.UserExam;
import com.sang.nv.education.exam.domain.command.UserExamCreateCmd;
import com.sang.nv.education.exam.domain.repository.ExamDomainRepository;
import com.sang.nv.education.exam.domain.repository.UserExamDomainRepository;
import com.sang.nv.education.exam.infrastructure.persistence.entity.UserExamEntity;
import com.sang.nv.education.exam.infrastructure.persistence.mapper.UserExamEntityMapper;
import com.sang.nv.education.exam.infrastructure.persistence.repository.ExamEntityRepository;
import com.sang.nv.education.exam.infrastructure.persistence.repository.ExamQuestionEntityRepository;
import com.sang.nv.education.exam.infrastructure.persistence.repository.QuestionEntityRepository;
import com.sang.nv.education.exam.infrastructure.persistence.repository.UserExamEntityRepository;
import com.sang.nv.education.exam.infrastructure.support.enums.UserExamStatus;
import com.sang.nv.education.exam.infrastructure.support.exception.BadRequestError;
import com.sang.nv.education.exam.infrastructure.support.exception.NotFoundError;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserExamServiceImpl implements UserExamService {
    private final ExamEntityRepository ExamEntityRepository;
    private final ExamQuestionEntityRepository examQuestionEntityRepository;
    private final ExamAutoMapper examAutoMapper;
    private final ExamDomainRepository examDomainRepository;
    private final UserExamDomainRepository userExamDomainRepository;
    private final UserExamEntityRepository userExamEntityRepository;
    private final UserExamEntityMapper userExamEntityMapper;

    public UserExamServiceImpl(ExamEntityRepository ExamEntityRepository,
                               QuestionEntityRepository questionEntityRepository,
                               ExamQuestionEntityRepository examQuestionEntityRepository,
                               ExamAutoMapper examAutoMapper,
                               ExamDomainRepository ExamDomainRepository, UserExamDomainRepository userExamDomainRepository, UserExamEntityRepository examEntityRepository, UserExamEntityMapper userExamEntityMapper) {
        this.ExamEntityRepository = ExamEntityRepository;
        this.examQuestionEntityRepository = examQuestionEntityRepository;
        this.examAutoMapper = examAutoMapper;
        this.examDomainRepository = ExamDomainRepository;
        this.userExamDomainRepository = userExamDomainRepository;
        this.userExamEntityRepository = examEntityRepository;
        this.userExamEntityMapper = userExamEntityMapper;
    }

    @Override
    public UserExamResult send(String id, UserExamCreateRequest request) {
        UserExamCreateCmd cmd = this.examAutoMapper.from(request);
        Exam exam = this.examDomainRepository.getById(request.getExamId());
        UserExam userExam = this.userExamDomainRepository.getById(id);
        if (Objects.equals(userExam.getStatus(), UserExamStatus.DONE)) {
            throw new ResponseException(BadRequestError.USER_EXAM_FINISHED);
        }
        userExam.update(cmd, exam.getExamQuestions());
//        UserExam userExam = new UserExam(cmd, exam.getExamQuestions());
        this.userExamDomainRepository.save(userExam);
        Long duration = 0L;
        if (Objects.nonNull(userExam.getTimeStart()) && Objects.nonNull(userExam.getTimeEnd())) {
            duration = Duration.between(userExam.getTimeStart(), userExam.getTimeEnd()).toMinutes();
        }
        return UserExamResult.builder()
                .userId(userExam.getUserId())
                .examId(userExam.getExamId())
                .point(userExam.getTotalPoint())
                .totalPoint(userExam.getMaxPoint())
                .user(userExam.getUser())
                .timeStart(userExam.getTimeStart())
                .timeEnd(userExam.getTimeEnd())
                .totalTimeUsed(duration)
                .userExamId(id)
                .build();
    }

    @Override
    public UserExam getById(String id) {
        return this.userExamDomainRepository.getById(id);
    }

    @Override
    public UserExam testingExam(String id) {
        UserExamEntity userExamEntity = this.userExamEntityRepository.findById(id).orElseThrow(() -> new ResponseException(NotFoundError.USER_EXAM_NOT_FOUND));
        UserExam userExam = this.userExamEntityMapper.toDomain(userExamEntity);
        if (Objects.equals(userExam.getStatus(), UserExamStatus.DONE)) {
            throw new ResponseException(BadRequestError.USER_EXAM_FINISHED);
        }
        userExam.startTesting();
        userExam.updateStatus(UserExamStatus.DOING);
        this.userExamDomainRepository.save(userExam);
        return userExam;
    }

    @Override
    public UserExam getByExamIdAndPeriodId(String examId, String periodId) {
        return this.userExamDomainRepository.findByExamIdAndPeriodId(examId, periodId);
    }

    @Override
    public PageDTO<UserExam> searchByRoomAndPeriod(String roomId, String periodId, UserRoomSearchRequest request) {
        Pageable pageable = PageableMapperUtil.toPageable(request);
        return this.userExamDomainRepository.searchUserExam(roomId, periodId, pageable);
    }

    @Override
    public PageDTO<UserExam> getMyExamByRoomAndPeriod(String roomId, String periodId, UserRoomSearchRequest request) {
        Pageable pageable = PageableMapperUtil.toPageable(request);
        Optional<String> userId = SecurityUtils.getCurrentUserLoginId();
        if (userId.isEmpty()) {
            throw new ResponseException(BadRequestError.USER_INVALID);
        }
        request.setUserIds(List.of(userId.get()));
        return this.userExamDomainRepository.getMyExam(roomId, periodId, request.getUserIds(), pageable);
    }
}
