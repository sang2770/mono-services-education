package com.sang.nv.education.iam.infrastructure.persistence.mapper;


import com.sang.commonmodel.mapper.EntityMapper;
import com.sang.nv.education.iam.domain.Role;
import com.sang.nv.education.iam.infrastructure.persistence.entity.RoleEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleEntityMapper extends EntityMapper<Role, RoleEntity> {
}
