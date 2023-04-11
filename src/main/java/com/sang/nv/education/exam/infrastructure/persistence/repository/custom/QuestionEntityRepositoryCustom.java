package com.sang.nv.education.exam.infrastructure.persistence.repository.custom;

import com.sang.nv.education.exam.infrastructure.persistence.entity.QuestionEntity;
import com.sang.nv.education.exam.infrastructure.persistence.query.QuestionSearchQuery;

import java.util.List;

public interface QuestionEntityRepositoryCustom {
    List<QuestionEntity> search(QuestionSearchQuery query);

    Long count(QuestionSearchQuery query);
}
