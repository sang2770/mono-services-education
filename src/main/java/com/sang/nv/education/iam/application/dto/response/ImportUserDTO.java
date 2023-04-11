package com.sang.nv.education.iam.application.dto.response;

import com.sang.nv.education.iamdomain.command.UserCreateOrUpdateCmd;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@Getter
@Setter
public class ImportUserDTO {
    private int rowIndex;
    private UserCreateOrUpdateCmd value;
    private Boolean check;
    private String errors;
}
