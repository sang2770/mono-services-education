package com.sang.nv.education.exam.application.dto.request;

import com.sang.commonmodel.dto.request.BaseSearchRequest;
import com.sang.nv.education.exam.infrastructure.support.enums.QuestionLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class QuestionSearchRequest extends BaseSearchRequest {
    List<QuestionLevel> questionLevels;
    String examId;
    List<String> subjectIds;
    List<String> groupIds;
}
