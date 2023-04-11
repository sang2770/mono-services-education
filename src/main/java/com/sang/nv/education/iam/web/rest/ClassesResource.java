package com.sang.nv.education.iam.web.rest;


import com.sang.commonmodel.dto.request.BaseSearchRequest;
import com.sang.commonmodel.dto.response.PagingResponse;
import com.sang.commonmodel.dto.response.Response;
import com.sang.commonmodel.validator.anotations.ValidatePaging;
import com.sang.nv.education.iamapplication.dto.request.Classes.ClassSearchRequest;
import com.sang.nv.education.iamapplication.dto.request.Classes.ClassesCreateOrUpdateRequest;
import com.sang.nv.education.iamdomain.Classes;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "Classes Resource")
@RequestMapping("/api")
public interface ClassesResource {
    @ApiOperation(value = "Search Classes")
    @GetMapping("/classes")
    PagingResponse<Classes> search(ClassSearchRequest request);

    @ApiOperation(value = "Create Classes")
    @PostMapping("/classes")
    Response<Classes> create(@RequestBody ClassesCreateOrUpdateRequest request);

    @ApiOperation(value = "Update Classes")
    @PostMapping("/classes/{id}/update")
    Response<Classes> update(@PathVariable String id, @RequestBody ClassesCreateOrUpdateRequest request);

    @ApiOperation(value = "Get Classes by id")
    @GetMapping("/classes/{id}")
    Response<Classes> getById(@PathVariable String id);

    @ApiOperation(value = "Get Classes by Department Id")
    @GetMapping("/find-by-department-id/{departmentId}")
    Response<List<Classes>> findByDepartmentId(@PathVariable String departmentId);

    @ApiOperation(value = "Get Classes autoComplete")
    @GetMapping("/classes/auto-complete")
    PagingResponse<Classes> autoComplete(@ValidatePaging(allowedSorts = {"name", "code"}) BaseSearchRequest request);
}
