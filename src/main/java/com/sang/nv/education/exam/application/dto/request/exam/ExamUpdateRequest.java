package com.sang.nv.education.exam.application.dto.request.exam;

import com.sang.commonmodel.dto.request.Request;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class ExamUpdateRequest extends Request {
    Float totalPoint;
    Integer numberQuestion;
    String periodId;
    String subjectId;
    String subjectName;
    String name;
    Long time;
    Long timeDelay;
    List<String> questionIds;

}
