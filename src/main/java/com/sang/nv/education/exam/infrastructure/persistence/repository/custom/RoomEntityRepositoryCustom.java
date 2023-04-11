package com.sang.nv.education.exam.infrastructure.persistence.repository.custom;

import com.sang.nv.education.exam.infrastructure.persistence.entity.RoomEntity;
import com.sang.nv.education.exam.infrastructure.persistence.query.RoomSearchQuery;

import java.util.List;

public interface RoomEntityRepositoryCustom {
    List<RoomEntity> search(RoomSearchQuery query);

    Long count(RoomSearchQuery query);
}
