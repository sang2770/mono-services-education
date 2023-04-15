package com.sang.nv.education.iam.infrastructure.persistence.repository.readmodel;

import com.sang.nv.education.iam.infrastructure.support.enums.UserType;
import lombok.Data;

@Data
public class StatisticUser {
    Integer numberUser;
    UserType userType;
    Integer month;

    public StatisticUser(UserType userType, Integer month, Long numberUser) {
        this.numberUser = numberUser.intValue();
        this.userType = userType;
        this.month = month;
    }
}
