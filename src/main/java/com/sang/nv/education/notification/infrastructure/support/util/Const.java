package com.sang.nv.education.notification.infrastructure.support.util;

import java.util.List;

public final class Const {

    public static final String DELIMITER = ",";
    public static final String SLASH = "/";
    public static final List<String> WHITELIST_FILE_TYPES = List.of("application/pdf");
    public static final String LOGO_PATH = "images/logo.jpg";
    public static final String LOGO_CONTENT_ID = "logoContentId";
    public static final String MESSAGE_TYPE = "MSG_NOTI_PARTNER_AMC";
    public static final String RECEIVER_APP_ID = "MOBILEAPP_AMC";
    public static final String RECEIVER_TYPE = "FCM_TOKEN";

    private Const() {}
}
