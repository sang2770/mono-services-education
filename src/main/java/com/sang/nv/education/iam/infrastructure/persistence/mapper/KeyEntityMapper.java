package com.sang.nv.education.iam.infrastructure.persistence.mapper;


import com.sang.commonmodel.mapper.EntityMapper;
import com.sang.nv.education.iam.domain.Key;
import com.sang.nv.education.iam.infrastructure.persistence.entity.KeyEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface KeyEntityMapper extends EntityMapper<Key, KeyEntity> {
}
