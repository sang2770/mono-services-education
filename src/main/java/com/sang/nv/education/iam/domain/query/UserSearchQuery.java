package com.sang.nv.education.iam.domain.query;

import com.sang.commonmodel.query.PagingQuery;
import com.sang.nv.education.iam.infrastructure.support.enums.UserStatus;
import com.sang.nv.education.iam.infrastructure.support.enums.UserType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
public class UserSearchQuery extends PagingQuery {

    private List<String> roleIds;
    private List<UserStatus> statuses;
    private List<UserType> userTypes;

    List<String> classIds;

}
