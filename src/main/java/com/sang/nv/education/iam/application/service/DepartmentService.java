package com.sang.nv.education.iam.application.service;

import com.sang.commonmodel.dto.PageDTO;
import com.sang.commonmodel.dto.request.BaseSearchRequest;
import com.sang.nv.education.iam.application.dto.request.DepartmentCreateOrUpdateRequest;
import com.sang.nv.education.iam.domain.Department;

public interface DepartmentService {
    /**
     * Create base price
     *
     * @param request DepartmentCreateRequest
     * @return Department
     */
    Department create(DepartmentCreateOrUpdateRequest request);

    /**
     * Update base price
     *
     * @param request DepartmentCreateOrUpdateRequest
     * @return Department
     */
    Department update(String id, DepartmentCreateOrUpdateRequest request);

    /**
     * Search base price
     *
     * @param request DepartmentSearchRequest
     * @return PageDTO<Department>
     */
    PageDTO<Department> search(BaseSearchRequest request);

    /**
     * Get detail base price
     *
     * @param id String
     * @return Department
     */
    Department getById(String id);

    PageDTO<Department> autoComplete(BaseSearchRequest request);

}
