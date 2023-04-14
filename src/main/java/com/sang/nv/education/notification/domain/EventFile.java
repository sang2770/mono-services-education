package com.sang.nv.education.notification.domain;

import com.sang.commonmodel.domain.AuditableDomain;
import com.sang.commonutil.IdUtils;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Setter(AccessLevel.PRIVATE)
@Getter
public class EventFile extends AuditableDomain {

    private String id;

    private String fileId;

    private String originalName;

    private String viewUrl;

    private String downloadUrl;

    private String eventId;

    private Boolean deleted;

    public EventFile(String fileId, String eventId) {
        this.id = IdUtils.nextId();
        this.fileId = fileId;
        this.eventId = eventId;
        this.deleted = false;
    }

    public void enrichFile(String originalName, String viewUrl, String downloadUrl) {
        this.originalName = originalName;
        this.viewUrl = viewUrl;
        this.downloadUrl = downloadUrl;
    }

    public void delete() {
        this.deleted = true;
    }

    public void unDelete() {
        this.deleted = false;
    }
}
