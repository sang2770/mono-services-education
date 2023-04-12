package com.sang.nv.education.exam.presentation.web.rest.impl;

import com.sang.commonmodel.dto.request.BaseSearchRequest;
import com.sang.commonmodel.dto.response.PagingResponse;
import com.sang.commonmodel.dto.response.Response;
import com.sang.nv.education.exam.application.dto.request.BaseCreateOrUpdateRequest;
import com.sang.nv.education.exam.application.service.SubjectService;
import com.sang.nv.education.exam.domain.Subject;
import com.sang.nv.education.exam.presentation.web.rest.SubjectResource;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class SubjectResourceImpl implements SubjectResource {
    private SubjectService subjectsService;

    @Override
    public PagingResponse<Subject> search(BaseSearchRequest request) {
        return PagingResponse.of(this.subjectsService.search(request));
    }

    @Override
    public Response<Subject> create(BaseCreateOrUpdateRequest request) {
        return Response.of(this.subjectsService.create(request));
    }

    @Override
    public Response<Subject> update(String id, BaseCreateOrUpdateRequest request) {
        return Response.of(this.subjectsService.update(id, request));
    }

    @Override
    public Response<Subject> getById(String id) {
        return Response.of(this.subjectsService.getById(id));
    }
}
