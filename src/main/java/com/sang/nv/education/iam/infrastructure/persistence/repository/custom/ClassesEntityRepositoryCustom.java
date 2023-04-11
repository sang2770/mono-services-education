package com.sang.nv.education.iam.infrastructure.persistence.repository.custom;



import com.sang.nv.education.iamdomain.query.ClassSearchQuery;
import com.sang.nv.education.iaminfrastructure.persistence.entity.ClassEntity;

import java.util.List;

public interface ClassesEntityRepositoryCustom {
    List<ClassEntity> search(ClassSearchQuery querySearch);

    Long count(ClassSearchQuery querySearch);

}
