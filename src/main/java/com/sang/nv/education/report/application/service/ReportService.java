package com.sang.nv.education.report.application.service;

import com.sang.nv.education.report.application.dto.request.ReportGeneralRequest;
import com.sang.nv.education.report.domain.GeneralReport;

public interface ReportService {
    GeneralReport generalReport(ReportGeneralRequest request);
}
