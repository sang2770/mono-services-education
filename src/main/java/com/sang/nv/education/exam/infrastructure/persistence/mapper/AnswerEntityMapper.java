package com.sang.nv.education.exam.infrastructure.persistence.mapper;

import com.sang.commonmodel.mapper.EntityMapper;
import com.sang.nv.education.exam.domain.Answer;
import com.sang.nv.education.exam.infrastructure.persistence.entity.AnswerEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AnswerEntityMapper extends EntityMapper<Answer, AnswerEntity> {
}
