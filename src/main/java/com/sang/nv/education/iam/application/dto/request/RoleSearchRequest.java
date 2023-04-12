package com.sang.nv.education.iam.application.dto.request;


import com.sang.commonmodel.dto.request.PagingRequest;
import com.sang.nv.education.iam.infrastructure.support.enums.RoleStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.Instant;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class RoleSearchRequest extends PagingRequest {

    private List<Boolean> isRoots;

    private List<RoleStatus> statuses;

    private Instant startAt;

    private List<String> createdBy;

    private Instant endAt;

    private String keyword;
}
