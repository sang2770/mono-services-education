package com.sang.nv.education.exam.infrastructure.persistence.repository.custom;

import com.sang.nv.education.exam.infrastructure.persistence.entity.ExamEntity;
import com.sang.nv.education.exam.infrastructure.persistence.query.ExamSearchQuery;

import java.util.List;

public interface ExamEntityRepositoryCustom {
    List<ExamEntity> search(ExamSearchQuery query);

    Long count(ExamSearchQuery query);
}
