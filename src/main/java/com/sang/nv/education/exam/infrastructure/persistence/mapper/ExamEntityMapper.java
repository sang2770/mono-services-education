package com.sang.nv.education.exam.infrastructure.persistence.mapper;

import com.sang.commonmodel.mapper.EntityMapper;
import com.sang.nv.education.exam.domain.Exam;
import com.sang.nv.education.exam.infrastructure.persistence.entity.ExamEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ExamEntityMapper extends EntityMapper<Exam, ExamEntity> {
}
