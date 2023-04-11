package com.sang.nv.education.exam.domain.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupQuestionCreateOrUpdateCmd extends BaseCreateOrUpdateCmd {
    String subjectId;
}
