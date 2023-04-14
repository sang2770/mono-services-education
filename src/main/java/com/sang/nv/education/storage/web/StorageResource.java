package com.sang.nv.education.storage.web;

import com.sang.commonmodel.dto.request.BaseSearchRequest;
import com.sang.commonmodel.dto.response.PagingResponse;
import com.sang.commonmodel.dto.response.Response;
import com.sang.nv.education.storage.domain.FileDomain;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Api(tags = "Storage resource")
@RequestMapping("/api")
public interface StorageResource {
    @ApiOperation("Send Storage with attack")
    @PostMapping("/storages/upload")
    Response<FileDomain> uploadFileBase(@RequestParam("file")MultipartFile file) throws Exception;

    @ApiOperation("Find file by id")
    @GetMapping("/storages/find-by-id/{id}")
    Response<FileDomain> findById(@PathVariable String id);

    @ApiOperation("Search file")
    @GetMapping("/storages/search")
    PagingResponse<FileDomain> search(BaseSearchRequest request);

    @ApiOperation("Save multiple file ")
    @PostMapping("/storages/upload-multiple")
    Response<List<FileDomain>> uploadMultiple(@RequestParam("files") List<MultipartFile> files);

}
