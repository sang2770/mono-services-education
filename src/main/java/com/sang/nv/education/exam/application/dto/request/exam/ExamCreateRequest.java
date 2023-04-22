package com.sang.nv.education.exam.application.dto.request;

import com.sang.commonmodel.dto.request.Request;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class ExamCreateRequest extends Request {

    String name;
    Float totalPoint;
    Integer numberQuestion;
    String periodId;
    String subjectId;
    String subjectName;
    Long time;

    Long timeDelay;
    List<String> questionIds;
}
