package com.sang.nv.education.iam.application.mapper;

import com.sang.nv.education.iam.application.dto.request.Classes.ClassSearchRequest;
import com.sang.nv.education.iam.application.dto.request.User.UserSearchRequest;
import com.sang.nv.education.iam.domain.query.ClassSearchQuery;
import com.sang.nv.education.iam.domain.query.UserSearchQuery;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IamAutoMapperQuery {
    UserSearchQuery toQuery(UserSearchRequest request);

    ClassSearchQuery from(ClassSearchRequest request);

}
