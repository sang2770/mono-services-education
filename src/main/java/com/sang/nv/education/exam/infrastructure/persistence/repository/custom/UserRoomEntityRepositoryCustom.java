package com.sang.nv.education.exam.infrastructure.persistence.repository.custom;

import com.sang.nv.education.exam.infrastructure.persistence.entity.UserRoomEntity;
import com.sang.nv.education.exam.infrastructure.persistence.query.UserRoomSearchQuery;

import java.util.List;

public interface UserRoomEntityRepositoryCustom {
    List<UserRoomEntity> search(UserRoomSearchQuery query);

    Long count(UserRoomSearchQuery query);
}
