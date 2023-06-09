package com.sang.nv.education.exam.presentation.web.rest.impl;

import com.sang.commonmodel.dto.response.PagingResponse;
import com.sang.commonmodel.dto.response.Response;
import com.sang.nv.education.exam.application.dto.request.question.GroupQuestionRandomRequest;
import com.sang.nv.education.exam.application.dto.request.room.QuestionCreateRequest;
import com.sang.nv.education.exam.application.dto.request.room.QuestionSearchRequest;
import com.sang.nv.education.exam.application.dto.request.room.QuestionUpdateRequest;
import com.sang.nv.education.exam.application.dto.response.ImportQuestionResult;
import com.sang.nv.education.exam.application.service.QuestionService;
import com.sang.nv.education.exam.domain.Question;
import com.sang.nv.education.exam.presentation.web.rest.QuestionResource;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@AllArgsConstructor
public class QuestionResourceImpl implements QuestionResource {
    private QuestionService QuestionsService;

    @Override
    public PagingResponse<Question> search(QuestionSearchRequest request) {
        return PagingResponse.of(this.QuestionsService.search(request));
    }

    @Override
    public Response<Question> create(QuestionCreateRequest request) {
        return Response.of(this.QuestionsService.create(request));
    }

    @Override
    public Response<Question> update(String id, QuestionUpdateRequest request) {
        return Response.of(this.QuestionsService.update(id, request));
    }

    @Override
    public Response<Question> getById(String id) {
        return Response.of(this.QuestionsService.getById(id));
    }

    @Override
    public Response<List<Question>> getRandomQuestionByGroup(GroupQuestionRandomRequest request) {
        return Response.of(this.QuestionsService.getRandomQuestionByGroup(request));
    }

    @Override
    public Response<Void> delete(String id) {
        this.QuestionsService.delete(id);
        return Response.ok();
    }

    @Override
    public void downloadTemplate(HttpServletResponse response) {
        this.QuestionsService.downloadTemplateImportQuestions(response);
    }

    @Override
    public Response<ImportQuestionResult> importQuestion(MultipartFile file, HttpServletResponse response) {
        return Response.of(QuestionsService.importQuestion(file, response));
    }
}
