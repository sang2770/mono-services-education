package com.sang.nv.education.exam.application.dto.request.exam;

import com.sang.commonmodel.dto.request.Request;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class UserExamCreateRequest extends Request {
    private String periodId;
}
