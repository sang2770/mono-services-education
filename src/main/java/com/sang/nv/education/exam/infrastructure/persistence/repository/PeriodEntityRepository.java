package com.sang.nv.education.exam.infrastructure.persistence.repository;


import com.sang.nv.education.exam.infrastructure.persistence.entity.PeriodEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PeriodEntityRepository extends JpaRepository<PeriodEntity, String>{

    @Query("from PeriodEntity u where u.deleted = false and ( :keyword is null or ( u.name like :keyword))")
    Page<PeriodEntity> search(@Param("keyword") String keyword, Pageable pageable);
    @Query("from PeriodEntity u where u.id in :ids")
    List<PeriodEntity> findAllByIds(@Param("ids") List<String> ids);

    @Query("select count(u) from PeriodEntity u left join PeriodRoomEntity p  where u.deleted = false and ( :roomIds is null or p.roomId in :roomIds)")
    int countAll(@Param("roomIds") List<String> roomIds);

}
