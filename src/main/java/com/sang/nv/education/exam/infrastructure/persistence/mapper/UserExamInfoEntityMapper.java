package com.sang.nv.education.exam.infrastructure.persistence.mapper;

import com.sang.commonmodel.mapper.EntityMapper;
import com.sang.nv.education.exam.domain.UserExamInfo;
import com.sang.nv.education.exam.infrastructure.persistence.entity.UserExamInfoEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserExamInfoEntityMapper extends EntityMapper<UserExamInfo, UserExamInfoEntity> {
}
