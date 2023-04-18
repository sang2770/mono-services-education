package com.sang.nv.education.storage.infrastructure.persistence.repository.impl;

import com.sang.commonpersistence.repository.BaseRepositoryImpl;
import com.sang.nv.education.storage.infrastructure.persistence.entity.FileEntity;
import org.springframework.stereotype.Repository;

@Repository
public class FileEntityRepositoryImpl extends BaseRepositoryImpl<FileEntity> {
    @Override
    public Class getClassName() {
        return FileEntity.class;
    }
}
