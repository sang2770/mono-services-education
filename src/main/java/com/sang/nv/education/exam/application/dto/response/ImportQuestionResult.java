package com.sang.nv.education.exam.application.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ImportQuestionResult {
    Boolean status;
    List<ImportQuestionDTO> data;
}
