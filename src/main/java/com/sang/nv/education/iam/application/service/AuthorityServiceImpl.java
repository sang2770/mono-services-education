package com.sang.nv.education.iam.application.service;

import com.sang.commonmodel.auth.UserAuthority;
import com.sang.commonmodel.exception.ResponseException;
import com.sang.commonweb.security.AuthorityService;
import com.sang.nv.education.iamdomain.Client;
import com.sang.nv.education.iamdomain.repository.ClientDomainRepository;
import com.sang.nv.education.iaminfrastructure.persistence.entity.RoleEntity;
import com.sang.nv.education.iaminfrastructure.persistence.entity.RolePermissionEntity;
import com.sang.nv.education.iaminfrastructure.persistence.entity.UserEntity;
import com.sang.nv.education.iaminfrastructure.persistence.entity.UserRoleEntity;
import com.sang.nv.education.iaminfrastructure.persistence.mapper.RoleEntityMapper;
import com.sang.nv.education.iaminfrastructure.persistence.mapper.RolePermissionEntityMapper;
import com.sang.nv.education.iaminfrastructure.persistence.repository.RoleEntityRepository;
import com.sang.nv.education.iaminfrastructure.persistence.repository.RolePermissionEntityRepository;
import com.sang.nv.education.iaminfrastructure.persistence.repository.UserEntityRepository;
import com.sang.nv.education.iaminfrastructure.persistence.repository.UserRoleEntityRepository;
import com.sang.nv.education.iaminfrastructure.support.enums.RoleStatus;
import com.sang.nv.education.iaminfrastructure.support.enums.UserStatus;
import com.sang.nv.education.iaminfrastructure.support.exception.AuthorizationError;
import com.sang.nv.education.iaminfrastructure.support.exception.BadRequestError;
import com.sang.nv.education.iaminfrastructure.support.exception.NotFoundError;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Primary
@Slf4j
@AllArgsConstructor
public class AuthorityServiceImpl implements AuthorityService {

    private final UserRoleEntityRepository userRoleEntityRepository;
    private final RoleEntityRepository roleEntityRepository;
    private final RolePermissionEntityRepository rolePermissionEntityRepository;
    private final UserEntityRepository userEntityRepository;
    private final ClientDomainRepository clientDomainRepository;
    private final RolePermissionEntityMapper rolePermissionEntityMapper;
    private final RoleEntityMapper roleEntityMapper;

    @Cacheable(cacheNames = "user-authority", key = "#userId",
            condition = "#userId != null", unless = "#userId == null || #result == null")
    @Override
    public UserAuthority getUserAuthority(String userId) {
        UserEntity userEntity = ensureUserExisted(userId);

        if (UserStatus.INACTIVE.equals(userEntity.getStatus())) {
            throw new ResponseException(BadRequestError.LOGIN_FAIL_DUE_INACTIVE_ACCOUNT);
        }

        List<String> grantedAuthorities = new ArrayList<>();
        boolean isRoot = false;
        List<UserRoleEntity> userRoleEntities = this.userRoleEntityRepository.findAllByUserId(userEntity.getId());
        if (!CollectionUtils.isEmpty(userRoleEntities)) {
            List<String> roleIds = userRoleEntities.stream()
                    .map(UserRoleEntity::getRoleId).distinct().collect(Collectors.toList());
            List<RoleEntity> roleEntities = this.roleEntityRepository.findAllByIds(roleIds);
            roleIds = roleEntities.stream()
                    .filter(r -> Objects.equals(RoleStatus.ACTIVE, r.getStatus()))
                    .map(RoleEntity::getId).distinct().collect(Collectors.toList());
            isRoot = roleEntities.stream().anyMatch(r -> Boolean.TRUE.equals(r.getIsRoot()));
            List<RolePermissionEntity> rolePermissionEntities = this.rolePermissionEntityRepository.findAllByRoleIds(roleIds);
            if (!CollectionUtils.isEmpty(rolePermissionEntities)) {
                grantedAuthorities = rolePermissionEntities.stream()
                        .map(r -> String.format("%s:%s", r.getResourceCode().toLowerCase(), r.getScope().toString().toLowerCase()))
                        .distinct().collect(Collectors.toList());
            }
        } else {
            log.info("User {} don't has role", userId);
        }

        return UserAuthority.builder()
                .isRoot(isRoot)
                .grantedPermissions(grantedAuthorities)
                .userId(userEntity.getId())
                .build();
    }

    @Cacheable(cacheNames = "client-authority", key = "#clientId",
            condition = "#clientId != null", unless = "#clientId == null || #result == null")
    @Override
    public UserAuthority getClientAuthority(String clientId) {
        List<String> grantedAuthorities = new ArrayList<>();
        boolean isRoot = false;
        Client client = this.clientDomainRepository.getById(clientId);
//        if (ClientStatus.INACTIVE.equals(client.getStatus())) {
//            throw new ResponseException(BadRequestError.LOGIN_FAIL_DUE_INACTIVE_ACCOUNT);
//        }
//        if (Objects.nonNull(client.getRoleId())) {
//            Optional<RoleEntity> role = roleEntityRepository.findById(client.getRoleId());
//            if (role.isPresent()) {
//                if (RoleStatus.INACTIVE.equals(role.get().getStatus())) {
//                    throw new ResponseException(BadRequestError.ROLE_INVALID);
//                }
//                isRoot = role.get().getIsRoot();
//                grantedAuthorities = this.getGrantedAuthorityByRoleId(client.getRoleId());
//            }
//        } else {
//            log.info("Client {} don't has role", client);
////            if (ClientType.INTERNAL.equals(client.getType())) {
////                isRoot = true;
////            }
//        }

        return UserAuthority.builder()
                .isRoot(isRoot)
                .userId(clientId)
                .grantedPermissions(grantedAuthorities)
                .build();
    }

    private UserEntity ensureUserExisted(String userId) {
        return this.userEntityRepository.findById(userId).orElseThrow(() ->
                new ResponseException(NotFoundError.USER_NOT_FOUND));
    }

    private List<String> getGrantedAuthorityByRoleId(String roleId) {
        List<String> grantedAuthorities = new ArrayList<>();
        Optional<RoleEntity> roleEntity = this.roleEntityRepository.findById(roleId);
        if (roleEntity.isEmpty()) {
            return grantedAuthorities;
        }
        RoleEntity role = roleEntity.get();
        List<String> roleIds = Collections.singletonList(role.getId());
        List<RolePermissionEntity> rolePermissionEntities = this.rolePermissionEntityRepository.findAllByRoleIds(roleIds);

        if (!CollectionUtils.isEmpty(rolePermissionEntities)) {
            grantedAuthorities = rolePermissionEntities.stream()
                    .map(r -> String.format("%s:%s", r.getResourceCode().toLowerCase(), r.getScope().toString().toLowerCase()))
                    .distinct().collect(Collectors.toList());
        }
        return grantedAuthorities;
    }

    Boolean checkRoleAdminOfUser(String userId) {
        List<UserRoleEntity> userRoleEntities = userRoleEntityRepository.findAllByUserId(userId);
        if (CollectionUtils.isEmpty(userRoleEntities)) {
            throw new ResponseException(AuthorizationError.USER_DO_NOT_HAVE_ROLE);
        } else {
            List<String> roleIds = userRoleEntities.stream().map(UserRoleEntity::getRoleId).collect(Collectors.toList());
            List<RoleEntity> roleEntities = roleEntityRepository.findAllByIds(roleIds);
            Optional<RoleEntity> roleAdmin = roleEntities.stream().filter(RoleEntity::getIsRoot).findFirst();
            return roleAdmin.isPresent();
        }
    }
}
