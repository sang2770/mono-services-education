package com.sang.nv.education.iam.infrastructure.domainrepository;


import com.sang.commonmodel.error.enums.NotFoundError;
import com.sang.commonmodel.exception.ResponseException;
import com.sang.nv.education.common.web.support.AbstractDomainRepository;
import com.sang.nv.education.iam.domain.Role;
import com.sang.nv.education.iam.domain.User;
import com.sang.nv.education.iam.domain.UserRole;
import com.sang.nv.education.iam.domain.repository.UserDomainRepository;
import com.sang.nv.education.iam.infrastructure.persistence.entity.UserEntity;
import com.sang.nv.education.iam.infrastructure.persistence.entity.UserRoleEntity;
import com.sang.nv.education.iam.infrastructure.persistence.mapper.RoleEntityMapper;
import com.sang.nv.education.iam.infrastructure.persistence.mapper.UserEntityMapper;
import com.sang.nv.education.iam.infrastructure.persistence.mapper.UserRoleEntityMapper;
import com.sang.nv.education.iam.infrastructure.persistence.repository.RoleEntityRepository;
import com.sang.nv.education.iam.infrastructure.persistence.repository.UserEntityRepository;
import com.sang.nv.education.iam.infrastructure.persistence.repository.UserRoleEntityRepository;
import io.jsonwebtoken.lang.Collections;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserDomainRepositoryImpl extends AbstractDomainRepository<User, UserEntity, String> implements UserDomainRepository {

    private final UserEntityRepository userEntityRepository;
    private final UserRoleEntityRepository userRoleEntityRepository;
    private final UserEntityMapper userEntityMapper;
    private final UserRoleEntityMapper userRoleEntityMapper;
    private final RoleEntityRepository roleEntityRepository;
    private final RoleEntityMapper roleEntityMapper;


    public UserDomainRepositoryImpl(UserEntityRepository userEntityRepository,
                                    UserRoleEntityRepository userRoleEntityRepository, UserEntityMapper userEntityMapper, UserRoleEntityMapper userRoleEntityMapper, RoleEntityRepository roleEntityRepository, RoleEntityMapper roleEntityMapper) {
        super(userEntityRepository, userEntityMapper);

        this.userEntityRepository = userEntityRepository;
        this.userRoleEntityRepository = userRoleEntityRepository;
        this.userEntityMapper = userEntityMapper;
        this.userRoleEntityMapper = userRoleEntityMapper;
        this.roleEntityRepository = roleEntityRepository;
        this.roleEntityMapper = roleEntityMapper;
    }

    @Override
    @Transactional
    public User save(User domain) {
        UserEntity userEntity = userEntityMapper.toEntity(domain);
        this.userEntityRepository.save(userEntity);
        // save user role
        List<UserRole> userRoles = domain.getUserRoles();
        if (!Collections.isEmpty(userRoles)) {
            List<UserRoleEntity> userRoleEntities = this.userRoleEntityMapper.toEntity(userRoles);
            this.userRoleEntityRepository.saveAll(userRoleEntities);
        }
        return domain;
    }

    @Override
    public User getById(String id) {
        return this.findById(id).orElseThrow(() ->
                new ResponseException(NotFoundError.USER_NOT_FOUND));
    }

    @Override
    protected User enrich(User user) {
//        Enrich role
        List<UserRole> userRoles = this.userRoleEntityMapper.toDomain(this.userRoleEntityRepository.findAllByUserId(user.getId()));
        List<String> roleIds = userRoles.stream().map(UserRole::getRoleId).collect(Collectors.toList());
        List<Role> roles = this.roleEntityMapper.toDomain(this.roleEntityRepository.findAllByIds(roleIds));
        user.enrichRoles(roles);
        user.enricUserRoles(userRoles);
        return user;
    }
}

