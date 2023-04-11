package com.sang.nv.education.exam.infrastructure.persistence.query;

import com.sang.commonmodel.query.PagingQuery;
import com.sang.nv.education.exam.infrastructure.support.enums.QuestionLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
public class QuestionSearchQuery extends PagingQuery {
    List<QuestionLevel> questionLevels;
    String examId;

    List<String> subjectIds;
    List<String> groupIds;
}
