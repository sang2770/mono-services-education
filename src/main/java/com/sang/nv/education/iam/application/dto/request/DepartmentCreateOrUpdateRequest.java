package com.sang.nv.education.iam.application.dto.request;

import com.sang.commonmodel.dto.request.Request;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.List;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class DepartmentCreateOrUpdateRequest extends Request {
    @NotBlank(message = "PHONE_REQUIRED")
    String phone;
    @NotBlank(message = "NAME_REQUIRED")
    String name;
    @NotBlank(message = "ADDRESS_REQUIRED")
    String address;
    List<String> classIds;
}
