package com.sang.nv.education.iam.application.dto.request.Auth;

import com.sang.commonmodel.dto.request.Request;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LogoutRequest extends Request {

    private String deviceId;

    private String deviceToken;

    private String refreshToken;
}
