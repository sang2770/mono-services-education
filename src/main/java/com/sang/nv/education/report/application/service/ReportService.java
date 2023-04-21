package com.sang.nv.education.report.application.service;

import com.sang.nv.education.exam.application.dto.response.UserExamResult;
import com.sang.nv.education.report.application.dto.request.NumberUserAndPeriodReportRequest;
import com.sang.nv.education.report.application.dto.request.ReportGeneralRequest;
import com.sang.nv.education.report.application.dto.request.UserExamReportRequest;
import com.sang.nv.education.report.domain.GeneralReport;
import com.sang.nv.education.report.domain.NumberUserAndPeriod;

import java.util.List;

public interface ReportService {
    GeneralReport generalReport(ReportGeneralRequest request);

    List<NumberUserAndPeriod> numberUserAndPeriodReport(NumberUserAndPeriodReportRequest request);

    List<UserExamResult> userExamReport(UserExamReportRequest request);
}
