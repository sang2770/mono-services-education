package com.sang.nv.education.iam.presentation.web.rest.impl;

import com.sang.commonmodel.dto.response.Response;
import com.sang.nv.education.iam.application.dto.request.Permission.PermissionUpdateRequest;
import com.sang.nv.education.iam.application.service.PermissionService;
import com.sang.nv.education.iam.domain.Permission;
import com.sang.nv.education.iam.presentation.web.rest.PermissionResource;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PermissionResourceImpl implements PermissionResource {

    private final PermissionService permissionService;

    public PermissionResourceImpl(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    /**
     * find all permission
     *
     * @return list permission
     */
    @Override
    public Response<List<Permission>> findAll() {
        List<Permission> permissions = this.permissionService.findAll();
        return Response.of(permissions);
    }

    @Override
    public Response<Permission> update(String id, PermissionUpdateRequest request) {
        Permission permission = this.permissionService.update(id, request);
        return Response.of(permission);
    }

    @Override
    public Response<Boolean> delete(String id) {
        this.permissionService.delete(id);
        return Response.of(Boolean.TRUE);
    }
}
