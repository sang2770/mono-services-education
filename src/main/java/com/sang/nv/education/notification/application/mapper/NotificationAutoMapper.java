package com.sang.nv.education.notification.application.mapper;

import com.sang.nv.education.notification.application.dto.request.EventCreateRequest;
import com.sang.nv.education.notification.application.dto.request.EventUpdateRequest;
import com.sang.nv.education.notification.application.dto.response.EventDTO;
import com.sang.nv.education.notification.domain.Event;
import com.sang.nv.education.notification.domain.command.EventCreateCommand;
import com.sang.nv.education.notification.domain.command.EventUpdateCommand;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface NotificationAutoMapper {

    EventCreateCommand from(EventCreateRequest request);

    EventUpdateCommand from(EventUpdateRequest request);

    List<EventDTO> fromEvent(List<Event> domains);

    List<EventConfigurationDTO> fromEventConfiguration(List<EventConfiguration> domains);
}
