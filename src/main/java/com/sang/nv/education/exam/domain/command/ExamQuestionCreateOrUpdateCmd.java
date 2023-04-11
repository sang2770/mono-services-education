package com.sang.nv.education.exam.domain.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExamQuestionCreateOrUpdateCmd {
    Float point;
    String examId;
    String questionId;
}
