package com.sang.nv.education.iam.application.service.Impl;

import com.sang.commonmodel.auth.UserAuthority;
import com.sang.commonmodel.dto.PageDTO;
import com.sang.commonmodel.dto.request.BaseSearchRequest;
import com.sang.commonmodel.error.enums.AuthenticationError;
import com.sang.commonmodel.exception.ResponseException;
import com.sang.commonmodel.mapper.util.PageableMapperUtil;
import com.sang.commonweb.security.AuthorityService;
import com.sang.commonweb.support.SecurityUtils;
import com.sang.nv.education.iamapplication.dto.request.Client.ClientCreateOrUpdateRequest;
import com.sang.nv.education.iamapplication.mapper.AutoMapper;
import com.sang.nv.education.iamapplication.service.ClientService;
import com.sang.nv.education.iamdomain.Client;
import com.sang.nv.education.iamdomain.command.ClientCreateOrUpdateCmd;
import com.sang.nv.education.iamdomain.repository.ClientDomainRepository;
import com.sang.nv.education.iaminfrastructure.persistence.entity.ClientEntity;
import com.sang.nv.education.iaminfrastructure.persistence.mapper.ClientEntityMapper;
import com.sang.nv.education.iaminfrastructure.persistence.repository.ClientEntityRepository;
import com.sang.nv.education.iaminfrastructure.support.exception.BadRequestError;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClientServiceImpl implements ClientService {
    private final AutoMapper autoMapper;
    private final ClientDomainRepository clientDomainRepository;
    private final ClientEntityRepository clientEntityRepository;
    private final ClientEntityMapper clientEntityMapper;
    private final AuthorityService authorityService;

    public ClientServiceImpl(AutoMapper autoMapper, ClientDomainRepository clientDomainRepository, ClientEntityRepository clientEntityRepository, ClientEntityMapper clientEntityMapper, AuthorityService authorityService) {
        this.autoMapper = autoMapper;
        this.clientDomainRepository = clientDomainRepository;
        this.clientEntityRepository = clientEntityRepository;
        this.clientEntityMapper = clientEntityMapper;
        this.authorityService = authorityService;
    }

    @Override
    public Client create(ClientCreateOrUpdateRequest request) {
        ClientCreateOrUpdateCmd cmd = this.autoMapper.from(request);
        Client Client = new Client(cmd);
        this.clientDomainRepository.save(Client);
        return Client;
    }

    @Override
    public Client update(String id, ClientCreateOrUpdateRequest request) {
        ClientCreateOrUpdateCmd cmd = this.autoMapper.from(request);
        Optional<Client> optionalClient = this.clientDomainRepository.findById(id);
        if (optionalClient.isEmpty()) {
            throw new ResponseException(BadRequestError.CLIENT_NOT_EXISTED);
        }
        Client client = optionalClient.get();
        client.update(cmd);
        this.clientDomainRepository.save(client);
        return client;
    }

    @Override
    public Client getById(String id) {
        return this.clientDomainRepository.findById(id).orElseThrow(() -> new ResponseException(BadRequestError.CLIENT_NOT_EXISTED));
    }

    @Override
    public PageDTO<Client> search(BaseSearchRequest request) {
        Pageable pageable = PageableMapperUtil.toPageable(request);
        Page<ClientEntity> clientEntityPage = this.clientEntityRepository.search(request.getKeyword(), pageable);
        List<Client> clients = this.clientEntityMapper.toDomain(clientEntityPage.stream().collect(Collectors.toList()));
        return PageDTO.of(clients, request.getPageIndex(), request.getPageSize(), clientEntityPage.getTotalElements());
    }

    @Override
    public UserAuthority getMyAuthority() {
        String clientId = SecurityUtils.getCurrentUserLoginId()
                .orElseThrow(() -> new ResponseException(AuthenticationError.UNAUTHORISED));
        return authorityService.getClientAuthority(clientId);
    }
}
