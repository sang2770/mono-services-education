package com.sang.nv.education.report.presentation.web.rest.impl;

import com.sang.commonmodel.dto.response.Response;
import com.sang.nv.education.report.application.dto.request.ReportGeneralRequest;
import com.sang.nv.education.report.application.service.ReportService;
import com.sang.nv.education.report.domain.GeneralReport;
import com.sang.nv.education.report.presentation.web.rest.ReportResource;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class ReportResourceImpl implements ReportResource {
    private final ReportService reportService;
    @Override
    public Response<GeneralReport> generalReport(ReportGeneralRequest request) {
        return Response.of(reportService.generalReport(request));
    }
}
