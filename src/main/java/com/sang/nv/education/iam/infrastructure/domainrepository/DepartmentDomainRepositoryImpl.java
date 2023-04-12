package com.sang.nv.education.iam.infrastructure.domainrepository;


import com.sang.commonmodel.exception.ResponseException;
import com.sang.commonweb.support.AbstractDomainRepository;
import com.sang.nv.education.iam.domain.Classes;
import com.sang.nv.education.iam.domain.Department;
import com.sang.nv.education.iam.domain.repository.DepartmentDomainRepository;
import com.sang.nv.education.iam.infrastructure.persistence.entity.DepartmentEntity;
import com.sang.nv.education.iam.infrastructure.persistence.mapper.ClassesEntityMapper;
import com.sang.nv.education.iam.infrastructure.persistence.mapper.DepartmentEntityMapper;
import com.sang.nv.education.iam.infrastructure.persistence.repository.ClassesEntityRepository;
import com.sang.nv.education.iam.infrastructure.persistence.repository.DepartmentEntityRepository;
import com.sang.nv.education.iam.infrastructure.support.exception.BadRequestError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class DepartmentDomainRepositoryImpl extends AbstractDomainRepository<Department, DepartmentEntity, String> implements DepartmentDomainRepository {
    private final DepartmentEntityRepository departmentEntityRepository;
    private final DepartmentEntityMapper departmentEntityMapper;
    private final ClassesEntityRepository classesEntityRepository;
    private final ClassesEntityMapper classesEntityMapper;

    public DepartmentDomainRepositoryImpl(DepartmentEntityRepository departmentEntityRepository,
                                          DepartmentEntityMapper departmentEntityMapper, ClassesEntityRepository classesEntityRepository, ClassesEntityMapper classesEntityMapper) {
        super(departmentEntityRepository, departmentEntityMapper);
        this.departmentEntityRepository = departmentEntityRepository;
        this.departmentEntityMapper = departmentEntityMapper;
        this.classesEntityRepository = classesEntityRepository;
        this.classesEntityMapper = classesEntityMapper;
    }

    @Override
    public Department getById(String id) {
        return this.findById(id).orElseThrow(() -> new ResponseException(BadRequestError.DEPARTMENT_NOT_EXISTED));
    }

    @Override
    protected Department enrich(Department department) {
        List<Classes> classes = this.classesEntityMapper.toDomain(this.classesEntityRepository.findByDepartmentId(department.getId()));
        department.enrichClasses(classes);
        return department;
    }
}

