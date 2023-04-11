package com.sang.nv.education.exam.infrastructure.persistence.mapper;

import com.sang.commonmodel.mapper.EntityMapper;
import com.sang.nv.education.exam.domain.UserExam;
import com.sang.nv.education.exam.infrastructure.persistence.entity.UserExamEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserExamEntityMapper extends EntityMapper<UserExam, UserExamEntity> {
}
