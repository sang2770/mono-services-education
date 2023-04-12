package com.sang.nv.education.iam.infrastructure.persistence.repository;


import com.sang.nv.education.iam.infrastructure.persistence.entity.RoleEntity;
import com.sang.nv.education.iam.infrastructure.support.enums.RoleStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleEntityRepository extends JpaRepository<RoleEntity, String> {

    @Query("from RoleEntity u where u.deleted = false and u.id in :ids")
    List<RoleEntity> findAllByIds(@Param("ids") List<String> ids);

    @Query("from RoleEntity u where u.deleted = false and u.code in :roleCodes")
    List<RoleEntity> findAllByCodes(@Param("roleCodes") List<String> roleCodes);

    @Query("select f from RoleEntity f where f.deleted = false" +
            " and (:keyword is null or (" +
            " f.name like :keyword or" +
            " f.code like :keyword " +
            " ))")
    Page<RoleEntity> search(@Param("keyword") String keyword, Pageable pageable);

    @Query("from RoleEntity u where u.deleted = false and u.isRoot = true")
    List<RoleEntity> findAllRootRole();

    @Query("from RoleEntity u where u.deleted = false and u.status = :status")
    List<RoleEntity> findAllByStatus(RoleStatus status);
}
