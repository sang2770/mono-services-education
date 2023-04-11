package com.sang.nv.education.exam.application.dto.request;

import com.sang.commonmodel.dto.request.Request;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@NoArgsConstructor
@Data
public class PeriodCreateOrUpdateRequest extends Request {
    @NotBlank(message = "NAME_REQUIRED")
    String name;
    @NotNull(message = "START_DATE_REQUIRED")
    LocalDate startDate;
    @NotNull(message = "END_DATE_REQUIRED")
    LocalDate endDate;
}
