package com.sang.nv.education.iam.application.service.Impl;


import com.sang.commonmodel.dto.PageDTO;
import com.sang.commonmodel.dto.request.BaseSearchRequest;
import com.sang.commonmodel.exception.ResponseException;
import com.sang.commonmodel.mapper.util.PageableMapperUtil;
import com.sang.nv.education.iam.application.dto.request.KeyCreateOrUpdateRequest;
import com.sang.nv.education.iam.application.mapper.IamAutoMapper;
import com.sang.nv.education.iam.application.service.KeysService;
import com.sang.nv.education.iam.domain.Key;
import com.sang.nv.education.iam.domain.command.KeyCreateOrUpdateCmd;
import com.sang.nv.education.iam.domain.repository.KeyDomainRepository;
import com.sang.nv.education.iam.infrastructure.persistence.entity.KeyEntity;
import com.sang.nv.education.iam.infrastructure.persistence.mapper.KeyEntityMapper;
import com.sang.nv.education.iam.infrastructure.persistence.repository.KeyEntityRepository;
import com.sang.nv.education.iam.infrastructure.support.exception.BadRequestError;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class KeyServiceImpl implements KeysService {
    private final KeyEntityRepository keyEntityRepository;
    private final IamAutoMapper autoMapper;
    private final KeyDomainRepository keyDomainRepository;
    private final KeyEntityMapper keyEntityMapper;

    public KeyServiceImpl(KeyEntityRepository KeyEntityRepository,
                          IamAutoMapper autoMapper,
                          KeyDomainRepository KeyDomainRepository,
                          KeyEntityMapper KeyEntityMapper) {
        this.keyEntityRepository = KeyEntityRepository;
        this.autoMapper = autoMapper;
        this.keyDomainRepository = KeyDomainRepository;
        this.keyEntityMapper = KeyEntityMapper;
    }

    @Override
    public Key create(KeyCreateOrUpdateRequest request) {
        KeyCreateOrUpdateCmd cmd = this.autoMapper.from(request);
        Key Key = new Key(cmd);
        this.keyDomainRepository.save(Key);
        return Key;
    }

    @Override
    public Key update(String id, KeyCreateOrUpdateRequest request) {
        KeyCreateOrUpdateCmd cmd = this.autoMapper.from(request);
        Optional<Key> optionalKey = this.keyDomainRepository.findById(id);
        if (optionalKey.isEmpty()) {
            throw new ResponseException(BadRequestError.KEYS_NOT_EXISTED);
        }
        Key Key = optionalKey.get();
        Key.update(cmd);
        this.keyDomainRepository.save(Key);
        return Key;
    }

    @Override
    public PageDTO<Key> search(BaseSearchRequest request) {
        Pageable pageable = PageableMapperUtil.toPageable(request);
        Page<KeyEntity> keyEntityPage = this.keyEntityRepository.search(request.getKeyword(), pageable);
        List<Key> keys = keyEntityPage.getContent().stream().map(Key::new).collect(Collectors.toList());
        return PageDTO.of(keys, request.getPageIndex(), request.getPageSize(), keyEntityPage.getTotalElements());
    }

    @Override
    public Key getById(String id) {
        return this.keyDomainRepository.findById(id).orElseThrow(() -> new ResponseException(BadRequestError.KEYS_NOT_EXISTED));
    }

}
