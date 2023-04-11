package com.sang.nv.education.exam.infrastructure.persistence.mapper;

import com.sang.commonmodel.mapper.EntityMapper;
import com.sang.nv.education.exam.domain.UserRoom;
import com.sang.nv.education.exam.infrastructure.persistence.entity.UserRoomEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserRoomEntityMapper extends EntityMapper<UserRoom, UserRoomEntity> {
}
