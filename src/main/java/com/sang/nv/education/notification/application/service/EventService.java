package com.sang.nv.education.notification.application.service;


import com.sang.commonmodel.dto.PageDTO;
import com.sang.nv.education.notification.application.dto.request.EventCreateRequest;
import com.sang.nv.education.notification.application.dto.request.EventSearchRequest;
import com.sang.nv.education.notification.application.dto.request.EventUpdateRequest;
import com.sang.nv.education.notification.application.dto.response.EventDTO;
import com.sang.nv.education.notification.domain.Event;

public interface EventService {
    /**
     * Tạo event và bắn notification toi device of user
     *
     * @param request
     * @return
     */
    Event create(EventCreateRequest request);

    /**
     * chi duoc phep cap nhat nhung event o trang thai waiting
     *
     * @param request
     * @return
     */
    Event update(String id, EventUpdateRequest request);

    Event getById(String id);

    /**
     * search event
     *
     * @param searchRequest
     * @return
     */
    PageDTO<EventDTO> search(EventSearchRequest searchRequest);

    /**
     * Cancel event which is waiting
     *
     * @param uuid
     */
    void cancel(String uuid);

    void send(String id);

    void delete(String id);

}
