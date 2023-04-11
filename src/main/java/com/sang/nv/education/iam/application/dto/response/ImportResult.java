package com.sang.nv.education.iam.application.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ImportResult {
    Boolean status;
    List<ImportUserDTO> data;
}
