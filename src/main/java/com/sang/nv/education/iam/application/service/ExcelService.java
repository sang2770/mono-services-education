package com.sang.nv.education.iam.application.service;

import com.sang.nv.education.iam.application.dto.request.User.UserExportRequest;
import com.sang.nv.education.iam.application.dto.response.ImportResult;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface ExcelService {
    void exportUsers(UserExportRequest request, HttpServletResponse response);
    void downloadUserTemplate(HttpServletResponse response);

    ImportResult importUser(MultipartFile file, HttpServletResponse response);

}
