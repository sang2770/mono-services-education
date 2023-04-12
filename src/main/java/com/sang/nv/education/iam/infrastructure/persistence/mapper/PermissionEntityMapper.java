package com.sang.nv.education.iam.infrastructure.persistence.mapper;


import com.sang.commonmodel.mapper.EntityMapper;
import com.sang.nv.education.iam.domain.Permission;
import com.sang.nv.education.iam.infrastructure.persistence.entity.PermissionEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PermissionEntityMapper extends EntityMapper<Permission, PermissionEntity> {
}
