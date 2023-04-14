package com.sang.nv.education.notification.infrastructure.persistence.mapper;

import com.sang.commonmodel.mapper.EntityMapper;
import com.sang.nv.education.notification.domain.EventFile;
import com.sang.nv.education.notification.infrastructure.persistence.entity.EventFileEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EventFileEntityMapper extends EntityMapper<EventFile, EventFileEntity> {}
