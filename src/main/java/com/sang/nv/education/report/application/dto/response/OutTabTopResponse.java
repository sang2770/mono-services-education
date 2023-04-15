package com.sang.nv.education.report.application.dto.response;

import com.sang.nv.education.iam.domain.User;
import lombok.Data;

@Data
public class OutTabTopResponse {
    User user;
    Integer numberOutTab;

}
