package com.sang.nv.education.exam.application.service;

import com.sang.nv.education.exam.application.dto.response.ImportQuestionResult;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

public interface ExamExcelService {
    void downloadQuestionTemplate(HttpServletResponse response);

    ImportQuestionResult importQuestions(MultipartFile file, HttpServletResponse response);
}
