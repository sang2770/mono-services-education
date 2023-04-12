package com.sang.nv.education.exam.presentation.web.rest;


import com.sang.commonmodel.dto.request.BaseSearchRequest;
import com.sang.commonmodel.dto.response.PagingResponse;
import com.sang.commonmodel.dto.response.Response;
import com.sang.nv.education.exam.application.dto.request.GroupQuestionCreateOrUpdateRequest;
import com.sang.nv.education.exam.domain.GroupQuestion;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Api(tags = "GroupQuestion Resource")
@RequestMapping("/api")
public interface GroupQuestionResource {
    @ApiOperation(value = "Search GroupQuestion")
    @GetMapping("/groupQuestions")
    PagingResponse<GroupQuestion> search(BaseSearchRequest request);

    @ApiOperation(value = "Create GroupQuestion")
    @PostMapping("/groupQuestions")
    Response<GroupQuestion> create(@RequestBody GroupQuestionCreateOrUpdateRequest request);

    @ApiOperation(value = "Update GroupQuestion")
    @PostMapping("/groupQuestions/{id}/update")
    Response<GroupQuestion> update(@PathVariable String id, @RequestBody GroupQuestionCreateOrUpdateRequest request);

    @ApiOperation(value = "Get GroupQuestion by id")
    @GetMapping("/groupQuestions/{id}")
    Response<GroupQuestion> getById(@PathVariable String id);

}
