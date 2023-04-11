package com.sang.nv.education.exam.domain.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PeriodCreateOrUpdateCmd {
    String name;
    LocalDate startDate;
    String code;
    LocalDate endDate;
}
