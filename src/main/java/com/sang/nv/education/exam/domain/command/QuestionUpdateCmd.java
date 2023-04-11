package com.sang.nv.education.exam.domain.command;

import com.sang.nv.education.exam.domain.Answer;
import com.sang.nv.education.exam.infrastructure.support.enums.QuestionLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionUpdateCmd {
    String title;
    List<Answer> answers;
    QuestionLevel questionLevel;
    List<AnswerCreateOrUpdateCmd> extraAnswer;
}
