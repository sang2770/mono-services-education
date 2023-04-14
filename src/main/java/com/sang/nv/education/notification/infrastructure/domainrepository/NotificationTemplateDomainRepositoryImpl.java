package com.sang.nv.education.notification.infrastructure.domainrepository;

import com.mbamc.common.exception.ResponseException;
import com.mbamc.common.webapp.support.AbstractDomainRepository;
import com.sang.nv.education.notification.infrastructure.persistence.entity.NotificationTemplateEntity;
import com.sang.nv.education.notification.infrastructure.persistence.mapper.NotificationTemplateEntityMapper;
import com.sang.nv.education.notification.infrastructure.persistence.repository.NotificationTemplateEntityRepository;
import com.sang.nv.education.notification.infrastructure.support.exception.NotFoundError;
import org.springframework.stereotype.Repository;

@Repository
public class NotificationTemplateDomainRepositoryImpl
        extends AbstractDomainRepository<NotificationTemplate, NotificationTemplateEntity, String>
        implements DomainRepository<NotificationTemplate, String> {
    private final NotificationTemplateEntityRepository notificationTemplateEntityRepository;
    private final NotificationTemplateEntityMapper notificationTemplateEntityMapper;

    public NotificationTemplateDomainRepositoryImpl(
            NotificationTemplateEntityRepository notificationTemplateEntityRepository,
            NotificationTemplateEntityMapper notificationTemplateEntityMapper) {
        super(notificationTemplateEntityRepository, notificationTemplateEntityMapper);
        this.notificationTemplateEntityMapper = notificationTemplateEntityMapper;
        this.notificationTemplateEntityRepository = notificationTemplateEntityRepository;
    }

    @Override
    public NotificationTemplate getById(String id) {
        return this.findById(id)
                .orElseThrow(() -> new ResponseException(NotFoundError.EVENT_NOT_FOUND));
    }

    @Override
    public NotificationTemplate save(NotificationTemplate notificationTemplate) {
        NotificationTemplateEntity notificationTemplateEntity =
                notificationTemplateEntityMapper.toEntity(notificationTemplate);
        this.notificationTemplateEntityRepository.save(notificationTemplateEntity);
        return notificationTemplate;
    }
}
