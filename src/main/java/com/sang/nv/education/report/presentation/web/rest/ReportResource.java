package com.sang.nv.education.report.presentation.web.rest;


import com.sang.commonmodel.dto.response.Response;
import com.sang.nv.education.report.application.dto.request.ReportGeneralRequest;
import com.sang.nv.education.report.domain.GeneralReport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Api(tags = "Key Resource")
@RequestMapping("/api")
public interface ReportResource {
    @ApiOperation(value = "Report general")
    @GetMapping("/report/general")
    Response<GeneralReport> generalReport(ReportGeneralRequest request);
}
