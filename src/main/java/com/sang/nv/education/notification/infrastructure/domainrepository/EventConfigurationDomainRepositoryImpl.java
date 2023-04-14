package com.sang.nv.education.notification.infrastructure.domainrepository;

import com.mbamc.common.exception.ResponseException;
import com.mbamc.common.webapp.support.AbstractDomainRepository;
import com.sang.nv.education.notification.infrastructure.persistence.entity.EventConfigurationEntity;
import com.sang.nv.education.notification.infrastructure.persistence.entity.EventConfigurationTargetEntity;
import com.sang.nv.education.notification.infrastructure.persistence.entity.NotificationTemplateEntity;
import com.sang.nv.education.notification.infrastructure.persistence.mapper.EventConfigurationEntityMapper;
import com.sang.nv.education.notification.infrastructure.persistence.mapper.EventConfigurationTargetEntityMapper;
import com.sang.nv.education.notification.infrastructure.persistence.mapper.NotificationTemplateEntityMapper;
import com.sang.nv.education.notification.infrastructure.persistence.repository.EventConfigurationEntityRepository;
import com.sang.nv.education.notification.infrastructure.persistence.repository.EventConfigurationTargetEntityRepository;
import com.sang.nv.education.notification.infrastructure.persistence.repository.NotificationTemplateEntityRepository;
import com.sang.nv.education.notification.infrastructure.support.exception.NotFoundError;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class EventConfigurationDomainRepositoryImpl
        extends AbstractDomainRepository<EventConfiguration, EventConfigurationEntity, String>
        implements EventConfigurationDomainRepository {

    private final EventConfigurationEntityMapper eventConfigurationEntityMapper;

    private final EventConfigurationEntityRepository eventConfigurationEntityRepository;

    private final EventConfigurationTargetEntityRepository eventConfigurationTargetEntityRepository;

    private final EventConfigurationTargetEntityMapper eventConfigurationTargetEntityMapper;

    private final NotificationTemplateEntityMapper notificationTemplateEntityMapper;

    private final NotificationTemplateEntityRepository notificationTemplateEntityRepository;

    protected EventConfigurationDomainRepositoryImpl(
            EventConfigurationEntityMapper eventConfigurationEntityMapper,
            EventConfigurationEntityRepository eventConfigurationEntityRepository,
            EventConfigurationTargetEntityRepository eventConfigurationTargetEntityRepository,
            EventConfigurationTargetEntityMapper eventConfigurationTargetEntityMapper,
            NotificationTemplateEntityMapper notificationTemplateEntityMapper,
            NotificationTemplateEntityRepository notificationTemplateEntityRepository) {
        super(eventConfigurationEntityRepository, eventConfigurationEntityMapper);
        this.eventConfigurationEntityMapper = eventConfigurationEntityMapper;
        this.eventConfigurationEntityRepository = eventConfigurationEntityRepository;
        this.eventConfigurationTargetEntityRepository = eventConfigurationTargetEntityRepository;
        this.eventConfigurationTargetEntityMapper = eventConfigurationTargetEntityMapper;
        this.notificationTemplateEntityMapper = notificationTemplateEntityMapper;
        this.notificationTemplateEntityRepository = notificationTemplateEntityRepository;
    }

    @Override
    public EventConfiguration getById(String id) {
        return this.findById(id)
                .orElseThrow(
                        () -> new ResponseException(NotFoundError.EVEN_CONFIGURATION_NOT_FOUND));
    }

    @Override
    public EventConfiguration save(EventConfiguration eventConfiguration) {
        EventConfigurationEntity eventConfigurationEntity =
                this.eventConfigurationEntityMapper.toEntity(eventConfiguration);

        this.eventConfigurationEntityRepository.save(eventConfigurationEntity);

        List<EventConfigurationTarget> eventConfigurationTargets = eventConfiguration.getTargets();
        NotificationTemplate currentTemplate = eventConfiguration.getCurrentTemplate();

        if (!CollectionUtils.isEmpty(eventConfigurationTargets)) {
            List<EventConfigurationTargetEntity> eventConfigurationTargetEntities =
                    this.eventConfigurationTargetEntityMapper.toEntity(eventConfigurationTargets);

            this.eventConfigurationTargetEntityRepository.saveAll(eventConfigurationTargetEntities);
        }

        if (Objects.nonNull(currentTemplate)) {
            NotificationTemplateEntity notificationTemplateEntity =
                    this.notificationTemplateEntityMapper.toEntity(currentTemplate);
            this.notificationTemplateEntityRepository.save(notificationTemplateEntity);
        }

        return eventConfiguration;
    }

    @Override
    public List<EventConfiguration> saveAll(List<EventConfiguration> domains) {
        List<EventConfigurationEntity> eventConfigurationEntities =
                this.eventConfigurationEntityMapper.toEntity(domains);
        this.eventConfigurationEntityRepository.saveAll(eventConfigurationEntities);

        List<EventConfigurationTarget> eventConfigurationTargets = new ArrayList<>();
        List<NotificationTemplate> notificationTemplates = new ArrayList<>();

        domains.forEach(
                domain -> {
                    if (!CollectionUtils.isEmpty(domain.getTargets())) {
                        eventConfigurationTargets.addAll(domain.getTargets());
                    }

                    if (Objects.nonNull(domain.getCurrentTemplate())) {
                        notificationTemplates.add(domain.getCurrentTemplate());
                    }
                });

        if (!CollectionUtils.isEmpty(eventConfigurationTargets)) {
            this.eventConfigurationTargetEntityRepository.saveAll(
                    this.eventConfigurationTargetEntityMapper.toEntity(eventConfigurationTargets));
        }

        if (!CollectionUtils.isEmpty(notificationTemplates)) {
            this.notificationTemplateEntityRepository.saveAll(
                    this.notificationTemplateEntityMapper.toEntity(notificationTemplates));
        }

        return domains;
    }

    @Override
    public List<EventConfiguration> enrichList(List<EventConfiguration> domains) {
        List<String> eventConfigurationIds =
                domains.stream().map(EventConfiguration::getId).collect(Collectors.toList());
        List<EventConfigurationTarget> eventConfigurationTargets =
                this.eventConfigurationTargetEntityMapper.toDomain(
                        this.eventConfigurationTargetEntityRepository
                                .findAllByEventConfigurationIds(eventConfigurationIds));

        List<NotificationTemplate> notificationTemplates =
                this.notificationTemplateEntityMapper.toDomain(
                        this.notificationTemplateEntityRepository.findAllByEventConfigurationIds(
                                eventConfigurationIds));

        domains.forEach(
                domain -> {
                    List<EventConfigurationTarget> eventConfigurationTargetList =
                            eventConfigurationTargets.stream()
                                    .filter(
                                            item ->
                                                    Objects.equals(
                                                            item.getEventConfigurationId(),
                                                            domain.getId()))
                                    .collect(Collectors.toList());

                    if (!CollectionUtils.isEmpty(eventConfigurationTargetList)) {
                        domain.enrichEventConfigurationTargets(eventConfigurationTargetList);
                    }

                    Optional<NotificationTemplate> optionalNotificationTemplate =
                            notificationTemplates.stream()
                                    .filter(
                                            item ->
                                                    Objects.equals(
                                                            item.getEventConfigurationId(),
                                                            domain.getId()))
                                    .findFirst();

                    optionalNotificationTemplate.ifPresent(domain::enrichCurrentTemplate);
                });
        return domains;
    }
}
