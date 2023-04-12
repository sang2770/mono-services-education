package com.sang.nv.education.exam.presentation.web.rest.impl;

import com.sang.commonmodel.dto.request.BaseSearchRequest;
import com.sang.commonmodel.dto.response.PagingResponse;
import com.sang.commonmodel.dto.response.Response;
import com.sang.nv.education.exam.application.dto.request.GroupQuestionCreateOrUpdateRequest;
import com.sang.nv.education.exam.application.service.GroupQuestionService;
import com.sang.nv.education.exam.domain.GroupQuestion;
import com.sang.nv.education.exam.presentation.web.rest.GroupQuestionResource;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class GroupQuestionResourceImpl implements GroupQuestionResource {
    private GroupQuestionService groupQuestionsService;

    @Override
    public PagingResponse<GroupQuestion> search(BaseSearchRequest request) {
        return PagingResponse.of(this.groupQuestionsService.search(request));
    }

    @Override
    public Response<GroupQuestion> create(GroupQuestionCreateOrUpdateRequest request) {
        return Response.of(this.groupQuestionsService.create(request));
    }

    @Override
    public Response<GroupQuestion> update(String id, GroupQuestionCreateOrUpdateRequest request) {
        return Response.of(this.groupQuestionsService.update(id, request));
    }

    @Override
    public Response<GroupQuestion> getById(String id) {
        return Response.of(this.groupQuestionsService.getById(id));
    }
}
