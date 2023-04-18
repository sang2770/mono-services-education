package com.sang.nv.education.storage.application.mapper;

import com.sang.commonmodel.mapper.EntityMapper;
import com.sang.nv.education.storage.domain.FileDomain;
import com.sang.nv.education.storage.infrastructure.persistence.entity.FileEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FileEntityMapper extends EntityMapper<FileDomain, FileEntity> {
}
