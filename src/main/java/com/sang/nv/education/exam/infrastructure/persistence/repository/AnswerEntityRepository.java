package com.sang.nv.education.exam.infrastructure.persistence.repository;


import com.sang.nv.education.exam.infrastructure.persistence.entity.AnswerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerEntityRepository extends JpaRepository<AnswerEntity, String> {
    @Query("from AnswerEntity u where u.deleted = false and u.questionId = :questionId")
    List<AnswerEntity> findByQuestionId(@Param("questionId") String questionId);

    @Query("from AnswerEntity u where u.deleted = false and u.questionId in :questionIds")
    List<AnswerEntity> findAllByQuestionIds(@Param("questionIds") List<String> questionIds);
}
