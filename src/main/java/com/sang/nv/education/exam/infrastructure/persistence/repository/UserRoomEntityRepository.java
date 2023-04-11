package com.sang.nv.education.exam.infrastructure.persistence.repository;


import com.sang.nv.education.exam.infrastructure.persistence.entity.UserRoomEntity;
import com.sang.nv.education.exam.infrastructure.persistence.repository.custom.UserRoomEntityRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRoomEntityRepository extends JpaRepository<UserRoomEntity, String>, UserRoomEntityRepositoryCustom {
    @Query("from UserRoomEntity u where u.deleted = false and u.roomId = :roomId ")
    List<UserRoomEntity> findByRoomId(@Param("roomId") String roomId);

    @Query("from UserRoomEntity u where u.deleted = false and u.roomId in :roomIds ")
    List<UserRoomEntity> findAllByRoomIds(@Param("roomIds") List<String> roomIds);
}

