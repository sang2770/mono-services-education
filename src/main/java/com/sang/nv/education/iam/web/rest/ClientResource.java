package com.sang.nv.education.iam.web.rest;


import com.sang.commonclient.request.iam.ClientLoginRequest;
import com.sang.commonmodel.auth.UserAuthority;
import com.sang.commonmodel.dto.request.BaseSearchRequest;
import com.sang.commonmodel.dto.response.PagingResponse;
import com.sang.commonmodel.dto.response.Response;
import com.sang.commonmodel.dto.response.iam.ClientToken;
import com.sang.nv.education.iamapplication.dto.request.Client.ClientCreateOrUpdateRequest;
import com.sang.nv.education.iamdomain.Client;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = "Client Resource")
@RequestMapping("/api")
@Validated
public interface
ClientResource {
    // ************************************* API FOR CLIENT **************************************

    @ApiOperation(value = "Client authentication")
    @PostMapping("/client/authenticate")
    Response<ClientToken> clientAuthentication(@RequestBody @Valid ClientLoginRequest request);

    @ApiOperation(value = "Create Client")
    @PostMapping("/clients")
    @PreAuthorize("hasPermission(null, 'client:create')")
    Response<Client> createClient(@RequestBody @Valid ClientCreateOrUpdateRequest request);

    @ApiOperation(value = "Update Client")
    @PostMapping("/clients/{id}/update")
    @PreAuthorize("hasPermission(null, 'client:update')")
    Response<Client> updateClient(@PathVariable String id, @RequestBody @Valid ClientCreateOrUpdateRequest request);

    @ApiOperation(value = "Search client")
    @GetMapping("/clients")
    @PreAuthorize("hasPermission(null, 'client:view')")
    PagingResponse<Client> search(BaseSearchRequest request);

    @ApiOperation(value = "get by id client")
    @GetMapping("/clients/{id}")
    @PreAuthorize("hasPermission(null, 'client:view')")
    Response<Client> getById(@PathVariable String id);

    @ApiOperation(value = "Get my authorities")
    @GetMapping("/clients/me/authorities")
    Response<UserAuthority> getAuthoritiesByClientId();

}
