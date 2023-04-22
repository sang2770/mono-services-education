package com.sang.nv.education.exam.application.dto.request.room;

import com.sang.commonmodel.dto.request.Request;
import com.sang.nv.education.exam.application.dto.request.question.AnswerCreateOrUpdateRequest;
import com.sang.nv.education.exam.infrastructure.support.enums.QuestionLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.List;

@NoArgsConstructor
@Data
public class QuestionCreateRequest extends Request {
    @NotBlank(message = "NAME_REQUIRED")
    String title;

    @NotBlank(message = "GROUP_ID_REQUIRED")
    String groupId;
    QuestionLevel questionLevel;

    List<AnswerCreateOrUpdateRequest> answerCreateOrUpdateRequests;
    List<String> questionFileIds;
}
