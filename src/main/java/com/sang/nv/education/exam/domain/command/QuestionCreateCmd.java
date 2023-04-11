package com.sang.nv.education.exam.domain.command;

import com.sang.nv.education.exam.infrastructure.support.enums.QuestionLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionCreateCmd {
    String title;
    String groupId;
    String subjectId;

    List<AnswerCreateOrUpdateCmd> answerCreateOrUpdateCmdList;
    QuestionLevel questionLevel;
}
