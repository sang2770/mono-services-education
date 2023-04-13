package com.sang.nv.education.iam.presentation.web.rest;

import com.sang.commonmodel.dto.response.Response;
import com.sang.nv.education.iam.application.dto.request.Permission.PermissionUpdateRequest;
import com.sang.nv.education.iam.domain.Permission;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Api(tags = "Permission Resource")
@RequestMapping("/api")
@Validated
public interface PermissionResource {

    @ApiOperation(value = "Find all permissions")
    @GetMapping("/permissions")
    @PreAuthorize("hasPermission(null, 'role:view')")
    Response<List<Permission>> findAll();

    @ApiOperation(value = "Update permission name")
    @PostMapping("/permissions/{id}/update")
    @PreAuthorize("hasPermission(null, 'role:update')")
    Response<Permission> update(@PathVariable String id, @RequestBody @Valid PermissionUpdateRequest request);

    @ApiOperation(value = "Delete permission")
    @PostMapping("/permissions/{id}/delete")
    @PreAuthorize("hasPermission(null, 'role:update')")
    Response<Boolean> delete(@PathVariable String id);

}
