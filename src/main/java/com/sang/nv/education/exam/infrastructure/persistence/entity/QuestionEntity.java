package com.sang.nv.education.exam.infrastructure.persistence.entity;

import com.sang.commonmodel.entity.AuditableEntity;
import com.sang.commonmodel.validator.ValidateConstraint;
import com.sang.nv.education.exam.infrastructure.support.enums.QuestionLevel;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "questions", indexes = {
        @Index(name = "questions_name_idx", columnList = "title"),
        @Index(name = "questions_deleted_idx", columnList = "deleted")
})
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class QuestionEntity extends AuditableEntity {
    @Id
    @Column(name = "id", length = ValidateConstraint.LENGTH.ID_MAX_LENGTH, nullable = false)
    private String id;

    @Column(name = "groupId", length = ValidateConstraint.LENGTH.ID_MAX_LENGTH, nullable = false)
    private String groupId;

    @Column(name = "subjectId", length = ValidateConstraint.LENGTH.ID_MAX_LENGTH, nullable = false)
    private String subjectId;

    @Column(name = "title", length = ValidateConstraint.LENGTH.NAME_MAX_LENGTH, nullable = false)
    private String title;

    @Column(name = "deleted", nullable = false)
    private Boolean deleted;

    @Column(name = "level", nullable = false)
    @Enumerated(EnumType.STRING)
    private QuestionLevel level;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        QuestionEntity that = (QuestionEntity) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}

