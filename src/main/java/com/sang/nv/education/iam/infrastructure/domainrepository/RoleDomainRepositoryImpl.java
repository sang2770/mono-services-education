package com.sang.nv.education.iam.infrastructure.domainrepository;

import com.sang.commonmodel.exception.ResponseException;
import com.sang.commonweb.support.AbstractDomainRepository;
import com.sang.nv.education.iamdomain.Role;
import com.sang.nv.education.iamdomain.RolePermission;
import com.sang.nv.education.iamdomain.repository.RoleDomainRepository;
import com.sang.nv.education.iaminfrastructure.persistence.entity.RoleEntity;
import com.sang.nv.education.iaminfrastructure.persistence.entity.RolePermissionEntity;
import com.sang.nv.education.iaminfrastructure.persistence.mapper.RoleEntityMapper;
import com.sang.nv.education.iaminfrastructure.persistence.mapper.RolePermissionEntityMapper;
import com.sang.nv.education.iaminfrastructure.persistence.repository.RoleEntityRepository;
import com.sang.nv.education.iaminfrastructure.persistence.repository.RolePermissionEntityRepository;
import com.sang.nv.education.iaminfrastructure.support.exception.NotFoundError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Slf4j
public class RoleDomainRepositoryImpl extends AbstractDomainRepository<Role, RoleEntity, String> implements RoleDomainRepository {

    private final RoleEntityRepository roleEntityRepository;
    private final RoleEntityMapper roleEntityMapper;
    private final RolePermissionEntityRepository rolePermissionEntityRepository;
    private final RolePermissionEntityMapper rolePermissionEntityMapper;

    public RoleDomainRepositoryImpl(RoleEntityMapper roleEntityMapper, RoleEntityRepository roleEntityRepository, RoleEntityRepository roleEntityRepository1,
                                    RoleEntityMapper roleEntityMapper1, RolePermissionEntityRepository rolePermissionEntityRepository,
                                    RolePermissionEntityMapper rolePermissionEntityMapper) {
        super(roleEntityRepository, roleEntityMapper);
        this.roleEntityRepository = roleEntityRepository1;
        this.roleEntityMapper = roleEntityMapper1;
        this.rolePermissionEntityRepository = rolePermissionEntityRepository;
        this.rolePermissionEntityMapper = rolePermissionEntityMapper;
    }

    @Override
    public Role getById(String id) {
        return this.findById(id).orElseThrow(() -> new ResponseException(NotFoundError.ROLE_NOT_FOUND));
    }

    @Override
    @Transactional
    public Role save(Role role) {
        RoleEntity roleEntity = this.roleEntityMapper.toEntity(role);
        this.roleEntityRepository.save(roleEntity);
        if (!CollectionUtils.isEmpty(role.getPermissions())) {
            List<RolePermissionEntity> rolePermissionEntities
                    = this.rolePermissionEntityMapper.toEntity(role.getPermissions());
            this.rolePermissionEntityRepository.saveAll(rolePermissionEntities);
        }
        return this.roleEntityMapper.toDomain(roleEntity);
    }

    @Override
    @Transactional
    public List<Role> enrichList(List<Role> roles) {
        List<String> roleIds = roles.stream().map(Role::getId).collect(Collectors.toList());
        List<RolePermissionEntity> rolePermissionEntities = this.rolePermissionEntityRepository.findAllByRoleIds(roleIds);
        List<RolePermission> rolePermissions = this.rolePermissionEntityMapper.toDomain(rolePermissionEntities);


        for (Role role : roles) {
            List<RolePermission> rolePermissionList = rolePermissions.stream()
                    .filter(item -> Objects.equals(item.getRoleId(), role.getId()))
                    .collect(Collectors.toList());
            if (!CollectionUtils.isEmpty(rolePermissionList)) {
                role.enrichPermissions(rolePermissionList);
            }
        }
        return roles;
    }
}
