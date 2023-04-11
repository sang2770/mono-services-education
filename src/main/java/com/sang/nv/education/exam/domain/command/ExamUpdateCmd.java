package com.sang.nv.education.exam.domain.command;

import lombok.Getter;

import java.util.List;
@Getter
public class ExamUpdateCmd {
    Float totalPoint;
    String periodId;
    String subjectId;
    String subjectName;
    String periodName;
    List<String> questionIds;
}
