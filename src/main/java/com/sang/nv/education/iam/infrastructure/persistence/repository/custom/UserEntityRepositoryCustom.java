package com.sang.nv.education.iam.infrastructure.persistence.repository.custom;


import com.sang.nv.education.iam.domain.query.UserSearchQuery;
import com.sang.nv.education.iam.infrastructure.persistence.entity.UserEntity;

import java.util.List;

public interface UserEntityRepositoryCustom {
    List<UserEntity> search(UserSearchQuery querySearch);

    Long countUser(UserSearchQuery querySearch);

}
