package com.sang.nv.education.iam.infrastructure.persistence.repository;


import com.sang.nv.education.iaminfrastructure.persistence.entity.ClientEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientEntityRepository extends JpaRepository<ClientEntity, String> {

    @Query("from ClientEntity ce where ce.deleted = false and ce.id = :clientId and ce.secret = :clientSecret")
    Optional<ClientEntity> findByClientIdAndSecret(@Param("clientId") String clientId,
                                                   @Param("clientSecret") String clientSecret);

    @Query("from ClientEntity ce where ce.deleted = false and ce.name = :clientName")
    Optional<ClientEntity> findByClientName(@Param("clientName") String clientName);

    @Query("from ClientEntity u where u.deleted = false and ( :keyword is null or ( u.name like :keyword))")
    Page<ClientEntity> search(@Param("keyword") String keyword, Pageable pageable);
}
