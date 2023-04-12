package com.sang.nv.education.iam.infrastructure.persistence.mapper;


import com.sang.commonmodel.mapper.EntityMapper;
import com.sang.nv.education.iam.domain.Department;
import com.sang.nv.education.iam.infrastructure.persistence.entity.DepartmentEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DepartmentEntityMapper extends EntityMapper<Department, DepartmentEntity> {
}
