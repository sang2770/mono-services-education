package com.sang.nv.education.exam.domain.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExamCreateCmd {
    String name;
    Float totalPoint;
    Integer numberQuestion;
    String periodId;
    String subjectId;
    String subjectName;
    String periodName;
    Long time;
    String code;
    Long timeDelay;
    List<String> questionIds;
}
