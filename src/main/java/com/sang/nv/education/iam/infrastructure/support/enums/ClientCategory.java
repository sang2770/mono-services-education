package com.sang.nv.education.iam.infrastructure.support.enums;

import lombok.Getter;

@Getter
public enum ClientCategory {
    IAM_CLIENT("IAM"),
    STORAGE_CLIENT("STORAGE"),
    EXAM_CLIENT("EXAM"),
    ROOM_CLIENT("ROOM");
    String clientName;
    ClientCategory(String clientName)
    {
        this.clientName = clientName;
    }
}
