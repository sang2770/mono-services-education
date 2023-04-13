package com.sang.nv.education.iam.application.service;

import com.sang.nv.education.iam.application.dto.request.Permission.PermissionUpdateRequest;
import com.sang.nv.education.iam.domain.Permission;

import java.util.List;

public interface PermissionService {

    /**
     * find all permission
     *
     * @return list permission
     */
    List<Permission> findAll();

    Permission update(String id, PermissionUpdateRequest request);

    void delete(String id);

}

