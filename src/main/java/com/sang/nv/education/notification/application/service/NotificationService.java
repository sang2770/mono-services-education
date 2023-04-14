package com.sang.nv.education.notification.application.service;

import com.sang.commonmodel.dto.PageDTO;
import com.sang.nv.education.common.web.support.DomainService;
import com.sang.nv.education.notification.application.dto.request.NotificationDeleteRequest;
import com.sang.nv.education.notification.application.dto.request.NotificationMarkReadRequest;
import com.sang.nv.education.notification.application.dto.request.NotificationMarkUnreadRequest;
import com.sang.nv.education.notification.application.dto.request.NotificationSearchRequest;
import com.sang.nv.education.notification.domain.Notification;

public interface NotificationService extends DomainService<Notification, String> {

    Notification ensureExisted(String uuid);

    Notification getNotificationByEventId(String eventId);

    Notification getNotificationById(String id);

    PageDTO<Notification> findAllByUserLogin(NotificationSearchRequest params);

    Boolean markAllRead(NotificationMarkReadRequest notificationMarkReadRequest);

    Boolean markAllUnreadByIds(NotificationMarkUnreadRequest notificationMarkUnreadRequest);

    Boolean markRead(String id);

    Boolean deleteAll(NotificationDeleteRequest notificationMarkReadRequest);

    Boolean delete(String id);

    Long countUnreadNotification();

    Boolean markReadAll();
}
