package com.sang.nv.education.exam.infrastructure.persistence.repository;


import com.sang.nv.education.exam.infrastructure.persistence.entity.GroupQuestionEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupQuestionEntityRepository extends JpaRepository<GroupQuestionEntity, String> {
    @Query("from GroupQuestionEntity u where u.deleted = false and ( :keyword is null or ( u.name like :keyword))")
    Page<GroupQuestionEntity> search(@Param("keyword") String keyword, Pageable pageable);

    @Query("from GroupQuestionEntity u where  u.id in :ids")
    List<GroupQuestionEntity> findAllByIds(@Param("ids") List<String> ids);
}
