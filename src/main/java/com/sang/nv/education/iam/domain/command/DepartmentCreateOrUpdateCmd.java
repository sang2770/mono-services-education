package com.sang.nv.education.iam.domain.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DepartmentCreateOrUpdateCmd {
    String phone;
    String name;
    String address;
    String code;
}
