package com.sang.nv.education.storage.web.impl;


import com.sang.commonmodel.dto.request.BaseSearchRequest;
import com.sang.commonmodel.dto.request.FindByIdsRequest;
import com.sang.commonmodel.dto.response.PagingResponse;
import com.sang.commonmodel.dto.response.Response;
import com.sang.nv.education.storage.application.service.StorageService;
import com.sang.nv.education.storage.domain.FileDomain;
import com.sang.nv.education.storage.web.StorageResource;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
public class StorageResourceImpl implements StorageResource {

    private final StorageService storageService;


    @Override
    public Response<FileDomain> uploadFileBase(MultipartFile file) throws Exception {
        return Response.of(this.storageService.firebaseUpload(file));
    }

    @Override
    public Response<FileDomain> findById(String id) {
        return Response.of(this.storageService.getById(id));
    }

    @Override
    public Response<List<FileDomain>> findById(FindByIdsRequest request) {
        return Response.of(this.storageService.getByIds(request.getIds()));
    }

    @Override
    public PagingResponse<FileDomain> search(BaseSearchRequest request) {
        return PagingResponse.of(this.storageService.search(request));
    }

    @Override
    public Response<List<FileDomain>> uploadMultiple(List<MultipartFile> files) {
        return Response.of(this.storageService.uploadMultipleFile(files));
    }
}
