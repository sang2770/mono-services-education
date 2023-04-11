package com.sang.nv.education.iam.infrastructure.persistence.repository.custom;


import com.sang.nv.education.iamdomain.query.UserSearchQuery;
import com.sang.nv.education.iaminfrastructure.persistence.entity.UserEntity;

import java.util.List;

public interface UserEntityRepositoryCustom {
    List<UserEntity> search(UserSearchQuery querySearch);

    Long countUser(UserSearchQuery querySearch);

}
