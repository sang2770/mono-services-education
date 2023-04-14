package com.sang.nv.education.notification.rest.impl;

import com.mbamc.common.dto.request.notification.IssueEventRequest;
import com.mbamc.common.dto.response.PagingResponse;
import com.mbamc.common.dto.response.Response;
import com.sang.nv.education.notification.application.dto.request.AutomaticEventSearchRequest;
import com.sang.nv.education.notification.application.dto.request.EventCreateRequest;
import com.sang.nv.education.notification.application.dto.request.EventSearchRequest;
import com.sang.nv.education.notification.application.dto.request.EventUpdateRequest;
import com.sang.nv.education.notification.application.dto.response.EventDTO;
import com.sang.nv.education.notification.application.service.EventService;
import com.sang.nv.education.notification.domain.Event;
import com.sang.nv.education.notification.presentation.rest.EventResource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Slf4j
public class EventResourceImpl implements EventResource {

    private final EventService eventService;

    public EventResourceImpl(EventService eventService) {
        this.eventService = eventService;
    }

    @Override
    public Response<Event> create(@Valid EventCreateRequest request) {
        Event event = eventService.create(request);
        return Response.of(event);
    }

    @Override
    public Response<Event> getById(String id) {
        Event event = eventService.getById(id);
        return Response.of(event);
    }

    @Override
    public Response<Event> update(String id, @Valid EventUpdateRequest request) {
        Event event = eventService.update(id, request);
        return Response.of(event);
    }

    @Override
    public PagingResponse<EventDTO> search(EventSearchRequest searchRequest) {
        return PagingResponse.of(eventService.search(searchRequest));
    }

    @Override
    public Response<Boolean> cancel(String id) {
        eventService.cancel(id);
        return Response.of(Boolean.TRUE);
    }

    @Override
    public Response<Boolean> delete(String id) {
        eventService.delete(id);
        return Response.of(Boolean.TRUE);
    }

    @Override
    public Response<Void> send(String id) {
        eventService.send(id);
        return Response.ok();
    }

    @Override
    public PagingResponse<AutomaticEventResponse> searchAutomaticEvent(
            AutomaticEventSearchRequest request) {
        return PagingResponse.of(eventService.searchAutomaticEvent(request));
    }

    @Override
    public Response<AutomaticEventDetailResponse> getAutomaticEventDetail(String id) {
        AutomaticEventDetailResponse automaticEventDetail =
                this.eventService.getAutomaticEventDetail(id);
        return Response.of(automaticEventDetail);
    }

    @Override
    public Response<Event> issued(IssueEventRequest request) {
        Event event = eventService.issued(request);
        return Response.of(event);
    }
}
