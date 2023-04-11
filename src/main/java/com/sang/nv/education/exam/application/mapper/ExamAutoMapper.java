package com.sang.nv.education.exam.application.mapper;


import com.sang.commonmodel.mapper.BaseAutoMapper;
import com.sang.nv.education.exam.application.dto.request.*;
import com.sang.nv.education.exam.domain.command.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface ExamAutoMapper extends BaseAutoMapper {
    BaseCreateOrUpdateCmd from(BaseCreateOrUpdateRequest request);

    ExamCreateCmd from(ExamCreateRequest request);

    RoomCreateOrUpdateCmd from(RoomCreateOrUpdateRequest request);

    ExamUpdateCmd from(ExamUpdateRequest request);

    @Mapping(target = "extraAnswer", source = "extraAnswer", qualifiedByName = "answerConvert")
    QuestionUpdateCmd from(QuestionUpdateRequest request);

    @Mapping(target = "answerCreateOrUpdateCmdList", source = "answerCreateOrUpdateRequests", qualifiedByName = "answerConvert")
    QuestionCreateCmd from(QuestionCreateRequest request);

    PeriodCreateOrUpdateCmd from(PeriodCreateOrUpdateRequest request);
    GroupQuestionCreateOrUpdateCmd from(GroupQuestionCreateOrUpdateRequest request);
    UserExamCreateCmd from(UserExamCreateRequest request);

    @Named("answerConvert")
    public static List<AnswerCreateOrUpdateCmd> answerConvert(List<AnswerCreateOrUpdateRequest> answerCreateOrUpdateRequests) {
        if (CollectionUtils.isEmpty(answerCreateOrUpdateRequests))
        {
            return new ArrayList<>();
        }
        return answerCreateOrUpdateRequests.stream().map(item ->
                AnswerCreateOrUpdateCmd.builder()
                        .content(item.getContent())
                        .status(item.getStatus()).build()).collect(Collectors.toList());
    }

}
