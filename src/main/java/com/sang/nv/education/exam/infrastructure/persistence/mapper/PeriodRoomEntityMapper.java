package com.sang.nv.education.exam.infrastructure.persistence.mapper;

import com.sang.commonmodel.mapper.EntityMapper;
import com.sang.nv.education.exam.domain.PeriodRoom;
import com.sang.nv.education.exam.infrastructure.persistence.entity.PeriodRoomEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PeriodRoomEntityMapper extends EntityMapper<PeriodRoom, PeriodRoomEntity> {
}
