package com.sang.nv.education.iam.presentation.web.rest.impl;

import com.sang.commonmodel.dto.request.BaseSearchRequest;
import com.sang.commonmodel.dto.response.PagingResponse;
import com.sang.commonmodel.dto.response.Response;
import com.sang.nv.education.iam.application.dto.request.KeyCreateOrUpdateRequest;
import com.sang.nv.education.iam.application.service.KeysService;
import com.sang.nv.education.iam.domain.Key;
import com.sang.nv.education.iam.presentation.web.rest.KeyResource;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class KeyResourceImpl implements KeyResource {
    private KeysService keysService;

    @Override
    public PagingResponse<Key> search(BaseSearchRequest request) {
        return PagingResponse.of(this.keysService.search(request));
    }

    @Override
    public Response<Key> create(KeyCreateOrUpdateRequest request) {
        return Response.of(this.keysService.create(request));
    }

    @Override
    public Response<Key> update(String id, KeyCreateOrUpdateRequest request) {
        return Response.of(this.keysService.update(id, request));
    }

    @Override
    public Response<Key> getById(String id) {
        return Response.of(this.keysService.getById(id));
    }
}
