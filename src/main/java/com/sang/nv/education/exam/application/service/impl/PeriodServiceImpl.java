package com.sang.nv.education.exam.application.service.impl;


import com.sang.commonmodel.dto.PageDTO;
import com.sang.commonmodel.dto.request.BaseSearchRequest;
import com.sang.commonmodel.exception.ResponseException;
import com.sang.commonmodel.mapper.util.PageableMapperUtil;
import com.sang.commonpersistence.support.SeqRepository;
import com.sang.nv.education.exam.application.dto.request.PeriodCreateOrUpdateRequest;
import com.sang.nv.education.exam.application.mapper.ExamAutoMapper;
import com.sang.nv.education.exam.application.service.PeriodService;
import com.sang.nv.education.exam.domain.Period;
import com.sang.nv.education.exam.domain.command.PeriodCreateOrUpdateCmd;
import com.sang.nv.education.exam.infrastructure.persistence.entity.PeriodEntity;
import com.sang.nv.education.exam.infrastructure.persistence.mapper.PeriodEntityMapper;
import com.sang.nv.education.exam.infrastructure.persistence.readmodel.StatisticPeriod;
import com.sang.nv.education.exam.infrastructure.persistence.repository.PeriodEntityRepository;
import com.sang.nv.education.exam.infrastructure.support.exception.BadRequestError;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PeriodServiceImpl implements PeriodService {
    private final PeriodEntityRepository periodEntityRepository;
    private final ExamAutoMapper examAutoMapper;
    private final PeriodEntityMapper periodEntityMapper;
    private final SeqRepository seqRepository;

    public PeriodServiceImpl(PeriodEntityRepository periodEntityRepository,
                             ExamAutoMapper examAutoMapper,
                             PeriodEntityMapper periodEntityMapper, SeqRepository seqRepository) {
        this.periodEntityRepository = periodEntityRepository;
        this.examAutoMapper = examAutoMapper;
        this.periodEntityMapper = periodEntityMapper;
        this.seqRepository = seqRepository;
    }

    @Override
    public Period create(PeriodCreateOrUpdateRequest request) {
        PeriodCreateOrUpdateCmd cmd = this.examAutoMapper.from(request);
        cmd.setCode(this.seqRepository.generatePeriodCode());
        Period period = new Period(cmd);
        this.periodEntityRepository.save(this.periodEntityMapper.toEntity(period));
        return period;
    }

    @Override
    public Period update(String id, PeriodCreateOrUpdateRequest request) {
        PeriodCreateOrUpdateCmd cmd = this.examAutoMapper.from(request);
        Optional<PeriodEntity> optionalPeriod = this.periodEntityRepository.findById(id);
        if (optionalPeriod.isEmpty()) {
            throw new ResponseException(BadRequestError.PERIOD_NOT_EXISTED);
        }
        Period period = this.periodEntityMapper.toDomain(optionalPeriod.get());
        period.update(cmd);
        this.periodEntityRepository.save(this.periodEntityMapper.toEntity(period));
        return period;
    }

    @Override
    public PageDTO<Period> search(BaseSearchRequest request) {
        Pageable pageable = PageableMapperUtil.toPageable(request);
        Page<PeriodEntity> PeriodEntityPage = this.periodEntityRepository.search(request.getKeyword(), pageable);
        List<Period> Period = PeriodEntityPage.getContent().stream().map(Period::new).collect(Collectors.toList());
        return PageDTO.of(Period, request.getPageIndex(), request.getPageSize(), PeriodEntityPage.getTotalElements());
    }

    @Override
    public Period getById(String id) {
        return this.periodEntityMapper.toDomain(this.periodEntityRepository.findById(id).orElseThrow(() -> new ResponseException(BadRequestError.PERIOD_NOT_EXISTED)));
    }

    @Override
    public void delete(String id) {
        Period period = this.getById(id);
        period.deleted();
        this.periodEntityRepository.save(this.periodEntityMapper.toEntity(period));
    }

    @Override
    public Integer count(List<String> roomIds) {
        return this.periodEntityRepository.countAll(roomIds);
    }

    @Override
    public List<StatisticPeriod> statisticPeriod(Integer year) {
        return this.periodEntityRepository.statisticPeriod(year);
    }
}
