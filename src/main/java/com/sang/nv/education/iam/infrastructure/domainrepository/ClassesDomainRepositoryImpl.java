package com.sang.nv.education.iam.infrastructure.domainrepository;


import com.sang.commonmodel.exception.ResponseException;
import com.sang.nv.education.common.web.support.AbstractDomainRepository;
import com.sang.nv.education.iam.domain.Classes;
import com.sang.nv.education.iam.domain.Department;
import com.sang.nv.education.iam.domain.Key;
import com.sang.nv.education.iam.domain.repository.ClassesDomainRepository;
import com.sang.nv.education.iam.infrastructure.persistence.entity.ClassEntity;
import com.sang.nv.education.iam.infrastructure.persistence.mapper.ClassesEntityMapper;
import com.sang.nv.education.iam.infrastructure.persistence.mapper.DepartmentEntityMapper;
import com.sang.nv.education.iam.infrastructure.persistence.mapper.KeyEntityMapper;
import com.sang.nv.education.iam.infrastructure.persistence.repository.ClassesEntityRepository;
import com.sang.nv.education.iam.infrastructure.persistence.repository.DepartmentEntityRepository;
import com.sang.nv.education.iam.infrastructure.persistence.repository.KeyEntityRepository;
import com.sang.nv.education.iam.infrastructure.support.exception.BadRequestError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ClassesDomainRepositoryImpl extends AbstractDomainRepository<Classes, ClassEntity, String> implements ClassesDomainRepository {
    private final ClassesEntityRepository classesEntityRepository;
    private final ClassesEntityMapper classesEntityMapper;
    private final DepartmentEntityRepository departmentEntityRepository;
    private final DepartmentEntityMapper departmentEntityMapper;
    private final KeyEntityRepository keyEntityRepository;
    private final KeyEntityMapper keyEntityMapper;

    public ClassesDomainRepositoryImpl(ClassesEntityRepository classesEntityRepository,
                                       ClassesEntityMapper classesEntityMapper, DepartmentEntityRepository departmentEntityRepository, DepartmentEntityMapper departmentEntityMapper, KeyEntityRepository keyEntityRepository, KeyEntityMapper keyEntityMapper) {
        super(classesEntityRepository, classesEntityMapper);
        this.classesEntityRepository = classesEntityRepository;
        this.classesEntityMapper = classesEntityMapper;
        this.departmentEntityRepository = departmentEntityRepository;
        this.departmentEntityMapper = departmentEntityMapper;
        this.keyEntityRepository = keyEntityRepository;
        this.keyEntityMapper = keyEntityMapper;
    }

    @Override
    public Classes getById(String id) {
        return this.findById(id).orElseThrow(() -> new ResponseException(BadRequestError.CLIENT_NOT_EXISTED));
    }

    @Override
    protected Classes enrich(Classes classes) {
        Department department = this.departmentEntityRepository.findById(classes.getDepartmentId()).map(this.departmentEntityMapper::toDomain).orElse(null);
        Key key = this.keyEntityRepository.findById(classes.getKeyId()).map(this.keyEntityMapper::toDomain).orElse(null);
        classes.enrichKey(key);
        classes.enrichDepartment(department);
        return super.enrich(classes);
    }

    @Override
    protected List<Classes> enrichList(List<Classes> classes) {
        return super.enrichList(classes);
    }
}

