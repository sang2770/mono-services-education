package com.sang.nv.education.iam.presentation.web.rest.impl;

import com.sang.commonmodel.dto.request.FindByIdsRequest;
import com.sang.commonmodel.dto.response.PagingResponse;
import com.sang.commonmodel.dto.response.Response;
import com.sang.nv.education.iam.application.dto.request.RoleCreateOrUpdateRequest;
import com.sang.nv.education.iam.application.dto.request.RoleSearchRequest;
import com.sang.nv.education.iam.application.service.RoleService;
import com.sang.nv.education.iam.domain.Role;
import com.sang.nv.education.iam.domain.UserRole;
import com.sang.nv.education.iam.presentation.web.rest.RoleResource;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class RoleResourceImpl implements RoleResource {

    private final RoleService roleService;

    public RoleResourceImpl(RoleService roleService) {
        this.roleService = roleService;
    }

    @Override
    public PagingResponse<Role> search(RoleSearchRequest request) {
        return PagingResponse.of(roleService.search(request));
    }

    @Override
    public Response<Role> create(@Valid RoleCreateOrUpdateRequest request) {
        Role role = this.roleService.create(request);
        return Response.of(role);
    }

    @Override
    public Response<Role> update(String id, @Valid RoleCreateOrUpdateRequest request) {
        Role role = this.roleService.update(id, request);
        return Response.of(role);
    }

    @Override
    public Response<Role> findById(String id) {
        Role role = this.roleService.getById(id);
        return Response.of(role);
    }

    @Override
    public Response<Role> delete(String id) {
        this.roleService.delete(id);
        return Response.ok();
    }

    @Override
    public Response<Boolean> active(String id) {
        this.roleService.active(id);
        return Response.ok();
    }

    @Override
    public Response<Boolean> inactive(String id) {
        this.roleService.inactive(id);
        return Response.ok();
    }

    @Override
    public Response<List<UserRole>> findALlByRoleIds(FindByIdsRequest request) {
        return Response.of(roleService.findUserRoleByRoleIds(request));
    }

}
