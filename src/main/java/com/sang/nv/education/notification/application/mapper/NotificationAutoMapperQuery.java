package com.sang.nv.education.notification.application.mapper;

import com.sang.nv.education.notification.application.dto.request.AutomaticEventSearchRequest;
import com.sang.nv.education.notification.application.dto.request.EventConfigurationSearchRequest;
import com.sang.nv.education.notification.application.dto.request.EventSearchRequest;
import com.sang.nv.education.notification.application.dto.request.NotificationSearchRequest;
import com.sang.nv.education.notification.application.dto.request.NotificationTemplateSearchRequest;
import com.sang.nv.education.notification.domain.query.AutomaticEventSearchQuery;
import com.sang.nv.education.notification.domain.query.EventSearchQuery;
import com.sang.nv.education.notification.domain.query.NotificationSearchQuery;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NotificationAutoMapperQuery {
    EventSearchQuery toQuery(EventSearchRequest eventSearchRequest);

    NotificationSearchQuery toQuery(NotificationSearchRequest notificationSearchRequest);

    EventConfigurationSearchQuery toQuery(EventConfigurationSearchRequest searchRequest);

    AutomaticEventSearchQuery toQuery(AutomaticEventSearchRequest request);

    NotificationTemplateSearchQuery toQuery(NotificationTemplateSearchRequest request);
}
