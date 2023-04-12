package com.sang.nv.education.iam.infrastructure.domainrepository;


import com.sang.commonmodel.exception.ResponseException;
import com.sang.commonweb.support.AbstractDomainRepository;
import com.sang.nv.education.iam.domain.Key;
import com.sang.nv.education.iam.domain.repository.KeyDomainRepository;
import com.sang.nv.education.iam.infrastructure.persistence.entity.KeyEntity;
import com.sang.nv.education.iam.infrastructure.persistence.mapper.KeyEntityMapper;
import com.sang.nv.education.iam.infrastructure.persistence.repository.KeyEntityRepository;
import com.sang.nv.education.iam.infrastructure.support.exception.BadRequestError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KeyDomainRepositoryImpl extends AbstractDomainRepository<Key, KeyEntity, String> implements KeyDomainRepository {
    private final KeyEntityRepository keyEntityRepository;
    private final KeyEntityMapper keyEntityMapper;

    public KeyDomainRepositoryImpl(KeyEntityRepository keyEntityRepository, KeyEntityMapper keyEntityMapper) {
        super(keyEntityRepository, keyEntityMapper);
        this.keyEntityRepository = keyEntityRepository;
        this.keyEntityMapper = keyEntityMapper;
    }


    @Override
    public Key getById(String id) {
        return this.findById(id).orElseThrow(() -> new ResponseException(BadRequestError.CLIENT_NOT_EXISTED));
    }
}

