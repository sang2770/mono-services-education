package com.sang.nv.education.exam.infrastructure.domainrepository;


import com.sang.commonmodel.exception.ResponseException;
import com.sang.nv.education.common.web.support.AbstractDomainRepository;
import com.sang.nv.education.exam.domain.GroupQuestion;
import com.sang.nv.education.exam.domain.Question;
import com.sang.nv.education.exam.domain.repository.GroupQuestionDomainRepository;
import com.sang.nv.education.exam.infrastructure.persistence.entity.GroupQuestionEntity;
import com.sang.nv.education.exam.infrastructure.persistence.mapper.GroupQuestionEntityMapper;
import com.sang.nv.education.exam.infrastructure.persistence.mapper.QuestionEntityMapper;
import com.sang.nv.education.exam.infrastructure.persistence.repository.GroupQuestionEntityRepository;
import com.sang.nv.education.exam.infrastructure.persistence.repository.QuestionEntityRepository;
import com.sang.nv.education.exam.infrastructure.support.exception.NotFoundError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class GroupQuestionDomainRepositoryImpl extends AbstractDomainRepository<GroupQuestion, GroupQuestionEntity, String> implements GroupQuestionDomainRepository {
    private final GroupQuestionEntityRepository groupQuestionEntityRepository;
    private final GroupQuestionEntityMapper groupQuestionEntityMapper;
    private final QuestionEntityMapper questionEntityMapper;
    private final QuestionEntityRepository questionEntityRepository;

    public GroupQuestionDomainRepositoryImpl(GroupQuestionEntityRepository GroupQuestionEntityRepository,
                                             GroupQuestionEntityMapper GroupQuestionEntityMapper, QuestionEntityMapper QuestionEntityMapper,
                                             QuestionEntityRepository QuestionEntityRepository) {
        super(GroupQuestionEntityRepository, GroupQuestionEntityMapper);
        this.groupQuestionEntityRepository = GroupQuestionEntityRepository;
        this.groupQuestionEntityMapper = GroupQuestionEntityMapper;
        this.questionEntityMapper = QuestionEntityMapper;
        this.questionEntityRepository = QuestionEntityRepository;
    }

    @Override
    public GroupQuestion getById(String id) {
        return this.findById(id).orElseThrow(() -> new ResponseException(NotFoundError.GROUP_NOT_EXISTED));
    }

    @Override
    public GroupQuestion save(GroupQuestion domain) {
        // save Question
        return super.save(domain);
    }


    @Override
    protected GroupQuestion enrich(GroupQuestion groupQuestion) {
        // enrich question
        List<Question> questions = this.questionEntityMapper.toDomain(this.questionEntityRepository.findByGroupId(groupQuestion.getId()));
        groupQuestion.enrichQuestions(questions);
        return super.enrich(groupQuestion);
    }
}

