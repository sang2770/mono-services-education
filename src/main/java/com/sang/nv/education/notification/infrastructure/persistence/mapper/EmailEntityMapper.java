package com.sang.nv.education.notification.infrastructure.persistence.mapper;

import com.sang.commonmodel.mapper.EntityMapper;
import com.sang.nv.education.notification.domain.Email;
import com.sang.nv.education.notification.infrastructure.persistence.entity.EmailEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmailEntityMapper extends EntityMapper<Email, EmailEntity> {}
