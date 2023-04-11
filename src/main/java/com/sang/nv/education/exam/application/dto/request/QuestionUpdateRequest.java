package com.sang.nv.education.exam.application.dto.request;

import com.sang.commonmodel.dto.request.Request;
import com.sang.nv.education.exam.domain.Answer;
import com.sang.nv.education.exam.infrastructure.support.enums.QuestionLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.List;

@NoArgsConstructor
@Data
public class QuestionUpdateRequest extends Request {
    @NotBlank(message = "NAME_REQUIRED")
    String title;

    String groupId;
    QuestionLevel questionLevel;
    List<Answer> answers;
    List<AnswerCreateOrUpdateRequest> extraAnswer;
}
