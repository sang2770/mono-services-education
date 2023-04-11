package com.sang.nv.education.exam.infrastructure.persistence.mapper;


import com.sang.commonmodel.mapper.EntityMapper;
import com.sang.nv.education.exam.domain.Period;
import com.sang.nv.education.exam.infrastructure.persistence.entity.PeriodEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PeriodEntityMapper extends EntityMapper<Period, PeriodEntity> {
}
