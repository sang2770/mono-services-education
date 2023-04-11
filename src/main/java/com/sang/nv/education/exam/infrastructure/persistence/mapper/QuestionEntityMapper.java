package com.sang.nv.education.exam.infrastructure.persistence.mapper;

import com.sang.commonmodel.mapper.EntityMapper;
import com.sang.nv.education.exam.domain.Question;
import com.sang.nv.education.exam.infrastructure.persistence.entity.QuestionEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface QuestionEntityMapper extends EntityMapper<Question, QuestionEntity> {
}
