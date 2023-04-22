package com.sang.nv.education.exam.presentation.web.rest;


import com.sang.commonmodel.dto.response.PagingResponse;
import com.sang.commonmodel.dto.response.Response;
import com.sang.nv.education.exam.application.dto.request.question.GroupQuestionRandomRequest;
import com.sang.nv.education.exam.application.dto.request.room.QuestionCreateRequest;
import com.sang.nv.education.exam.application.dto.request.room.QuestionSearchRequest;
import com.sang.nv.education.exam.application.dto.request.room.QuestionUpdateRequest;
import com.sang.nv.education.exam.domain.Question;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "Question Resource")
@RequestMapping("/api")
public interface QuestionResource {
    @ApiOperation(value = "Search Question")
    @GetMapping("/questions")
    PagingResponse<Question> search(QuestionSearchRequest request);

    @ApiOperation(value = "Create Question")
    @PostMapping("/questions")
    Response<Question> create(@RequestBody QuestionCreateRequest request);

    @ApiOperation(value = "Update Question")
    @PostMapping("/questions/{id}/update")
    Response<Question> update(@PathVariable String id, @RequestBody QuestionUpdateRequest request);

    @ApiOperation(value = "Get Question by id")
    @GetMapping("/questions/{id}")
    Response<Question> getById(@PathVariable String id);

    @ApiOperation(value = "Get random question by groupId")
    @GetMapping("/questions/random-question-by-group")
    Response<List<Question>> getRandomQuestionByGroup(GroupQuestionRandomRequest request);

    @ApiOperation(value = "Delete Question")
    @PostMapping("/questions/{id}/delete")
    Response<Void> delete(@PathVariable String id);

}
