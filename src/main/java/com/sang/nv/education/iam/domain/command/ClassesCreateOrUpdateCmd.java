package com.sang.nv.education.iam.domain.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClassesCreateOrUpdateCmd {
    String name;
    String keyId;
    String departmentId;
    String code;
}
