package com.sang.nv.education.exam.presentation.web.rest.impl;

import com.sang.commonmodel.dto.request.BaseSearchRequest;
import com.sang.commonmodel.dto.response.PagingResponse;
import com.sang.commonmodel.dto.response.Response;
import com.sang.nv.education.exam.application.dto.request.PeriodCreateOrUpdateRequest;
import com.sang.nv.education.exam.application.service.PeriodService;
import com.sang.nv.education.exam.domain.Period;
import com.sang.nv.education.exam.presentation.web.rest.PeriodResource;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class PeriodResourceImpl implements PeriodResource {
    private PeriodService periodsService;

    @Override
    public PagingResponse<Period> search(BaseSearchRequest request) {
        return PagingResponse.of(this.periodsService.search(request));
    }

    @Override
    public Response<Period> create(PeriodCreateOrUpdateRequest request) {
        return Response.of(this.periodsService.create(request));
    }

    @Override
    public Response<Period> update(String id, PeriodCreateOrUpdateRequest request) {
        return Response.of(this.periodsService.update(id, request));
    }

    @Override
    public Response<Period> getById(String id) {
        return Response.of(this.periodsService.getById(id));
    }

    @Override
    public Response<Void> delete(String id) {
        this.periodsService.delete(id);
        return Response.ok();
    }
}
