package com.sang.nv.education.exam.application.dto.request;

import com.sang.commonmodel.dto.request.Request;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

@NoArgsConstructor
@Data
public class UserExamCreateRequest extends Request {
    Instant timeEnd;
    Instant timeStart;
    String examId;
    String userId;
    String roomId;
    String periodId;
    List<UserExamInfoCreateRequest> userExamInfoCreateRequests;

    Integer numberOutTab;
}
