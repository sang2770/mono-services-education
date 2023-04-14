package com.sang.nv.education.notification.infrastructure.persistence.mapper;

import com.sang.commonmodel.mapper.EntityMapper;
import com.sang.nv.education.notification.domain.Event;
import com.sang.nv.education.notification.infrastructure.persistence.entity.EventEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface EventEntityMapper extends EntityMapper<Event, EventEntity> {

    @Mappings({})
    Event toDomain(EventEntity entity);

    @Mappings({})
    EventEntity toEntity(Event event);
}
