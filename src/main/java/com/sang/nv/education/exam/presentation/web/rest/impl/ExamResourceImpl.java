package com.sang.nv.education.exam.presentation.web.rest.impl;

import com.sang.commonmodel.dto.response.PagingResponse;
import com.sang.commonmodel.dto.response.Response;
import com.sang.nv.education.exam.application.dto.request.ExamCreateRequest;
import com.sang.nv.education.exam.application.dto.request.ExamSearchRequest;
import com.sang.nv.education.exam.application.dto.request.ExamUpdateRequest;
import com.sang.nv.education.exam.application.service.ExamService;
import com.sang.nv.education.exam.domain.Exam;
import com.sang.nv.education.exam.presentation.web.rest.ExamResource;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class ExamResourceImpl implements ExamResource {
    private ExamService ExamsService;

    @Override
    public PagingResponse<Exam> search(ExamSearchRequest request) {
        return PagingResponse.of(this.ExamsService.search(request));
    }

    @Override
    public Response<Exam> create(ExamCreateRequest request) {
        return Response.of(this.ExamsService.create(request));
    }

    @Override
    public Response<Exam> update(String id, ExamUpdateRequest request) {
        return Response.of(this.ExamsService.update(id, request));
    }

    @Override
    public Response<Exam> getById(String id) {
        return Response.of(this.ExamsService.getById(id));
    }

    @Override
    public PagingResponse<Exam> getExamByRoomId(String roomId, ExamSearchRequest request) {
        return PagingResponse.of(this.ExamsService.getByRoomId(roomId, request));
    }
}
