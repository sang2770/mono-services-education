package com.sang.nv.education.exam.infrastructure.persistence.mapper;

import com.sang.commonmodel.mapper.EntityMapper;
import com.sang.nv.education.exam.domain.GroupQuestion;
import com.sang.nv.education.exam.infrastructure.persistence.entity.GroupQuestionEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GroupQuestionEntityMapper extends EntityMapper<GroupQuestion, GroupQuestionEntity> {
}
