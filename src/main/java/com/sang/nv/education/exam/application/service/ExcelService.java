package com.sang.nv.education.exam.application.service;

import com.sang.nv.education.exam.application.dto.response.ImportQuestionResult;
import com.sang.nv.education.iam.application.dto.request.User.UserExportRequest;
import com.sang.nv.education.iam.application.dto.response.ImportResult;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

public interface ExcelService {
    void downloadQuestionTemplate(HttpServletResponse response);

    ImportQuestionResult importQuestions(MultipartFile file, HttpServletResponse response);

}
