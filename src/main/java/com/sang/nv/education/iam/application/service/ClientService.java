package com.sang.nv.education.iam.application.service;

import com.sang.commonmodel.auth.UserAuthority;
import com.sang.commonmodel.dto.PageDTO;
import com.sang.commonmodel.dto.request.BaseSearchRequest;
import com.sang.nv.education.iamapplication.dto.request.Client.ClientCreateOrUpdateRequest;
import com.sang.nv.education.iamdomain.Client;

public interface ClientService {
    Client create(ClientCreateOrUpdateRequest request);
    Client update(String id, ClientCreateOrUpdateRequest request);

    Client getById(String id);
    PageDTO<Client> search(BaseSearchRequest request);
    UserAuthority getMyAuthority();
}
