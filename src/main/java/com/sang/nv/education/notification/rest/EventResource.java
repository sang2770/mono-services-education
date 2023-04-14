package com.sang.nv.education.notification.rest;

import com.sang.commonmodel.dto.response.PagingResponse;
import com.sang.commonmodel.dto.response.Response;
import com.sang.commonmodel.validator.anotations.ValidatePaging;
import com.sang.nv.education.notification.application.dto.request.EventCreateRequest;
import com.sang.nv.education.notification.application.dto.request.EventSearchRequest;
import com.sang.nv.education.notification.application.dto.request.EventUpdateRequest;
import com.sang.nv.education.notification.application.dto.response.EventDTO;
import com.sang.nv.education.notification.domain.Event;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Api(tags = "Notification - Event Resource")
@RequestMapping("/api")
@Validated
public interface EventResource {

    @ApiOperation("Create event")
    @PreAuthorize("hasPermission(null, 'notification:create')")
    @PostMapping("/events")
    Response<Event> create(@RequestBody @Valid EventCreateRequest request);

    @ApiOperation("Get event")
    @PreAuthorize("hasPermission(null, 'notification:view')")
    @GetMapping("/events/{id}")
    Response<Event> getById(@PathVariable String id);

    @ApiOperation("Update Event")
    @PreAuthorize("hasPermission(null, 'notification:update')")
    @PostMapping("/events/{id}/update")
    Response<Event> update(@PathVariable String id, @RequestBody @Valid EventUpdateRequest request);

    @ApiOperation("Search event")
    @PreAuthorize("hasPermission(null, 'notification:view')")
    @GetMapping("/events")
    PagingResponse<EventDTO> search(
            @Valid @ValidatePaging(allowedSorts = {"createdAt", "lastModifiedAt"})
            EventSearchRequest searchRequest);

    @ApiOperation("Cancel event")
    @PreAuthorize("hasPermission(null, 'notification:update')")
    @PostMapping("/events/{id}/cancel")
    Response<Boolean> cancel(@PathVariable String id);

    @ApiOperation("Delete event")
    @PreAuthorize("hasPermission(null, 'notification:delete')")
    @PostMapping("/events/{id}/delete")
    Response<Boolean> delete(@PathVariable String id);

    @ApiOperation("Send event")
    @PreAuthorize("hasPermission(null, 'notification:update')")
    @PostMapping("/events/{id}/send")
    Response<Void> send(@PathVariable String id);

    @ApiOperation("Search automatic event")
    @PreAuthorize("hasPermission(null, 'notification:view')")
    @GetMapping("/events/automatic-events")
    PagingResponse<AutomaticEventResponse> searchAutomaticEvent(
            @Valid @ValidatePaging(allowedSorts = {"createdAt", "lastModifiedAt"})
            AutomaticEventSearchRequest request);

    @ApiOperation("Detail automatic event")
    @PreAuthorize("hasPermission(null, 'notification:view')")
    @GetMapping("/events/automatic-events/{id}")
    Response<AutomaticEventDetailResponse> getAutomaticEventDetail(@PathVariable String id);

}
