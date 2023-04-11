package com.sang.nv.education.exam.web.rest;


import com.sang.commonmodel.dto.response.PagingResponse;
import com.sang.commonmodel.dto.response.Response;
import com.sang.nv.education.exam.application.dto.request.ExamCreateRequest;
import com.sang.nv.education.exam.application.dto.request.ExamSearchRequest;
import com.sang.nv.education.exam.application.dto.request.ExamUpdateRequest;
import com.sang.nv.education.exam.domain.Exam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Exam Resource")
@RequestMapping("/api")
public interface ExamResource {
    @ApiOperation(value = "Search Exam")
    @GetMapping("/exams")
    PagingResponse<Exam> search(ExamSearchRequest request);

    @ApiOperation(value = "Create Exam")
    @PostMapping("/exams")
    Response<Exam> create(@RequestBody ExamCreateRequest request);

    @ApiOperation(value = "Update Exam")
    @PostMapping("/exams/{id}/update")
    Response<Exam> update(@PathVariable String id, @RequestBody ExamUpdateRequest request);

    @ApiOperation(value = "Get Exam by id")
    @GetMapping("/exams/{id}")
    Response<Exam> getById(@PathVariable String id);

    @ApiOperation(value = "Get Exam by room")
    @GetMapping("/exams/get-exam-by-room-id/{roomId}")
    PagingResponse<Exam> getExamByRoomId(@PathVariable String roomId, ExamSearchRequest request);
}
