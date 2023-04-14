package com.sang.nv.education.notification.infrastructure.domainrepository;


import com.sang.commonmodel.exception.ResponseException;
import com.sang.nv.education.common.web.support.AbstractDomainRepository;
import com.sang.nv.education.notification.domain.Email;
import com.sang.nv.education.notification.domain.Event;
import com.sang.nv.education.notification.domain.EventFile;
import com.sang.nv.education.notification.domain.Notification;
import com.sang.nv.education.notification.domain.repository.EventDomainRepository;
import com.sang.nv.education.notification.infrastructure.persistence.entity.EventEntity;
import com.sang.nv.education.notification.infrastructure.persistence.mapper.EmailEntityMapper;
import com.sang.nv.education.notification.infrastructure.persistence.mapper.EventEntityMapper;
import com.sang.nv.education.notification.infrastructure.persistence.mapper.EventFileEntityMapper;
import com.sang.nv.education.notification.infrastructure.persistence.mapper.EventTargetEntityMapper;
import com.sang.nv.education.notification.infrastructure.persistence.mapper.NotificationEntityMapper;
import com.sang.nv.education.notification.infrastructure.persistence.repository.EmailRepository;
import com.sang.nv.education.notification.infrastructure.persistence.repository.EventFileRepository;
import com.sang.nv.education.notification.infrastructure.persistence.repository.EventRepository;
import com.sang.nv.education.notification.infrastructure.persistence.repository.EventTargetRepository;
import com.sang.nv.education.notification.infrastructure.persistence.repository.NotificationRepository;
import com.sang.nv.education.notification.infrastructure.support.exception.NotFoundError;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class EventDomainRepositoryImpl extends AbstractDomainRepository<Event, EventEntity, String>
        implements EventDomainRepository {

    private final EventRepository eventRepository;
    private final EventEntityMapper eventEntityMapper;
    private final EventTargetRepository eventTargetRepository;
    private final EventTargetEntityMapper eventTargetEntityMapper;
    private final EventFileRepository eventFileRepository;
    private final EventFileEntityMapper eventFileEntityMapper;
    private final NotificationRepository notificationRepository;
    private final NotificationEntityMapper notificationEntityMapper;
    private final EmailRepository emailRepository;
    private final EmailEntityMapper emailEntityMapper;

    public EventDomainRepositoryImpl(
            EventRepository eventRepository,
            EventEntityMapper eventEntityMapper,
            EventTargetRepository eventTargetRepository,
            EventTargetEntityMapper eventTargetEntityMapper,
            EventFileRepository eventFileRepository,
            EventFileEntityMapper eventFileEntityMapper,
            NotificationRepository notificationRepository,
            NotificationEntityMapper notificationEntityMapper,
            EmailRepository emailRepository,
            EmailEntityMapper emailEntityMapper) {
        super(eventRepository, eventEntityMapper);
        this.eventRepository = eventRepository;
        this.eventEntityMapper = eventEntityMapper;
        this.eventTargetRepository = eventTargetRepository;
        this.eventTargetEntityMapper = eventTargetEntityMapper;
        this.eventFileRepository = eventFileRepository;
        this.eventFileEntityMapper = eventFileEntityMapper;
        this.notificationRepository = notificationRepository;
        this.notificationEntityMapper = notificationEntityMapper;
        this.emailRepository = emailRepository;
        this.emailEntityMapper = emailEntityMapper;
    }

    @Override
    public Event getById(String id) {
        return this.findById(id)
                .orElseThrow(() -> new ResponseException(NotFoundError.EVENT_NOT_FOUND));
    }

    @Override
    @Transactional
    public Event save(Event event) {
        EventEntity eventEntity = eventEntityMapper.toEntity(event);
        // this.findById(event.getId()).ifPresent(e -> eventEntity.setVersion(e.getVersion()));
        this.eventRepository.save(eventEntity);
        List<Notification> notifications = event.getNotifications();
        if (!CollectionUtils.isEmpty(notifications)) {
            notificationRepository.saveAll(notificationEntityMapper.toEntity(notifications));
        }
        List<EventFile> eventFiles = event.getEventFiles();
        if (!CollectionUtils.isEmpty(eventFiles)) {
            eventFileRepository.saveAll(eventFileEntityMapper.toEntity(eventFiles));
        }
        List<EventTarget> eventTargets = event.getEventTargets();
        if (!CollectionUtils.isEmpty(eventTargets)) {
            eventTargetRepository.saveAll(eventTargetEntityMapper.toEntity(eventTargets));
        }
        List<Email> emails = event.getEmails();
        if (!CollectionUtils.isEmpty(emails)) {
            emailRepository.saveAll(emailEntityMapper.toEntity(emails));
        }
        return event;
    }

    @Override
    public List<Event> enrichList(List<Event> events) {
        if (CollectionUtils.isEmpty(events)) {
            return new ArrayList<>();
        }
        List<String> eventIds = events.stream().map(Event::getId).collect(Collectors.toList());
        List<EventFile> eventFiles =
                eventFileRepository.findAllByEventIds(eventIds).stream()
                        .map(eventFileEntityMapper::toDomain)
                        .collect(Collectors.toList());
        List<Notification> notifications =
                notificationRepository.findAllByEventIds(eventIds).stream()
                        .map(notificationEntityMapper::toDomain)
                        .collect(Collectors.toList());
        List<EventTarget> eventTargets =
                eventTargetRepository.findByEventIds(eventIds).stream()
                        .map(eventTargetEntityMapper::toDomain)
                        .collect(Collectors.toList());
        List<Email> emails =
                emailRepository.findAllByEventIds(eventIds).stream()
                        .map(emailEntityMapper::toDomain)
                        .collect(Collectors.toList());

        events.forEach(
                event -> {
                    List<EventFile> eventFilesTmp =
                            eventFiles.stream()
                                    .filter(it -> Objects.equals(it.getEventId(), event.getId()))
                                    .collect(Collectors.toList());
                    event.enrichEventFile(eventFilesTmp);

                    List<Notification> notificationsTmp =
                            notifications.stream()
                                    .filter(it -> Objects.equals(it.getEventId(), event.getId()))
                                    .collect(Collectors.toList());
                    event.enrichNotification(notificationsTmp);

                    List<EventTarget> eventTargetsTmp =
                            eventTargets.stream()
                                    .filter(it -> Objects.equals(it.getEventId(), event.getId()))
                                    .collect(Collectors.toList());
                    event.enrichEventTargets(eventTargetsTmp);

                    List<Email> emailsTmp =
                            emails.stream()
                                    .filter(e -> Objects.equals(e.getEventId(), event.getId()))
                                    .collect(Collectors.toList());
                    event.enrichEmail(emailsTmp);
                });
        return events;
    }
}
