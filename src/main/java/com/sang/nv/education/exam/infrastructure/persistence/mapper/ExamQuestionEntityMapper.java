package com.sang.nv.education.exam.infrastructure.persistence.mapper;

import com.sang.commonmodel.mapper.EntityMapper;
import com.sang.nv.education.exam.domain.ExamQuestion;
import com.sang.nv.education.exam.infrastructure.persistence.entity.ExamQuestionEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ExamQuestionEntityMapper extends EntityMapper<ExamQuestion, ExamQuestionEntity> {
}
