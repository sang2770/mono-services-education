package com.sang.nv.education.iam.infrastructure.domainrepository;


import com.sang.commonmodel.exception.ResponseException;
import com.sang.commonweb.support.AbstractDomainRepository;
import com.sang.nv.education.iamdomain.Client;
import com.sang.nv.education.iamdomain.repository.ClientDomainRepository;
import com.sang.nv.education.iaminfrastructure.persistence.entity.ClientEntity;
import com.sang.nv.education.iaminfrastructure.persistence.mapper.ClientEntityMapper;
import com.sang.nv.education.iaminfrastructure.persistence.repository.ClientEntityRepository;
import com.sang.nv.education.iaminfrastructure.support.exception.BadRequestError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ClientDomainRepositoryImpl extends AbstractDomainRepository<Client, ClientEntity, String> implements ClientDomainRepository {
    private final ClientEntityRepository clientEntityRepository;
    private final ClientEntityMapper clientEntityMapper;

    public ClientDomainRepositoryImpl(ClientEntityRepository clientEntityRepository,
                                      ClientEntityMapper clientEntityMapper
                                      ) {
        super(clientEntityRepository, clientEntityMapper);
        this.clientEntityRepository = clientEntityRepository;
        this.clientEntityMapper = clientEntityMapper;
    }

    @Override
    public Client getById(String id) {
        return this.findById(id).orElseThrow(() -> new ResponseException(BadRequestError.CLIENT_NOT_EXISTED));
    }
}

