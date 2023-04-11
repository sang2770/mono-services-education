package com.sang.nv.education.iam.web.rest.impl;


import com.sang.commonclient.config.security.ClientAuthentication;
import com.sang.commonclient.request.iam.ClientLoginRequest;
import com.sang.commonmodel.auth.UserAuthority;
import com.sang.commonmodel.dto.request.BaseSearchRequest;
import com.sang.commonmodel.dto.response.PagingResponse;
import com.sang.commonmodel.dto.response.Response;
import com.sang.commonmodel.dto.response.iam.ClientToken;
import com.sang.nv.education.iamapplication.dto.request.Client.ClientCreateOrUpdateRequest;
import com.sang.nv.education.iamapplication.service.ClientService;
import com.sang.nv.education.iamdomain.Client;
import com.sang.nv.education.iampresentation.web.rest.ClientResource;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientResourceImpl implements ClientResource {

    private final ClientAuthentication clientAuthentication;
    private final ClientService clientService;

    public ClientResourceImpl(ClientAuthentication clientAuthentication, ClientService clientService) {
        this.clientAuthentication = clientAuthentication;
        this.clientService = clientService;
    }

    @Override
    public Response<ClientToken> clientAuthentication(ClientLoginRequest request) {
        return Response.of(clientAuthentication.getClientToken(request.getClientId(), request.getClientSecret()));
    }

    @Override
    public Response<Client> createClient(ClientCreateOrUpdateRequest request) {
        return Response.of(this.clientService.create(request));
    }

    @Override
    public Response<Client> updateClient(String id, ClientCreateOrUpdateRequest request) {
        return Response.of(this.clientService.update(id, request));

    }

    @Override
    public PagingResponse<Client> search(BaseSearchRequest request) {
        return PagingResponse.of(this.clientService.search(request));
    }

    @Override
    public Response<Client> getById(String id) {
        return Response.of(this.clientService.getById(id));

    }

    @Override
    public Response<UserAuthority> getAuthoritiesByClientId() {
        return Response.of(clientService.getMyAuthority());
    }
}
