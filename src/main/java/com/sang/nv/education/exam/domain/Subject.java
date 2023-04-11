package com.sang.nv.education.exam.domain;

import com.sang.commonutil.IdUtils;
import com.sang.nv.education.exam.domain.command.BaseCreateOrUpdateCmd;
import com.sang.nv.education.exam.infrastructure.persistence.entity.SubjectEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Setter(AccessLevel.PRIVATE)
@Getter
public class Subject {
    String id;
    String name;
    String code;
    Boolean deleted;

    public Subject(BaseCreateOrUpdateCmd cmd){
        this.id = IdUtils.nextId();
        this.code = cmd.getCode();
        this.name = cmd.getName();
        this.deleted = Boolean.FALSE;
    }

    public Subject(SubjectEntity cmd)
    {
        this.id = cmd.getId();
        this.name = cmd.getName();
        this.code = cmd.getCode();
        this.deleted = Boolean.FALSE;
    }

    public void update(BaseCreateOrUpdateCmd cmd)
    {
        this.name = cmd.getName();
        this.deleted = Boolean.FALSE;
    }

    public void deleted(){
        this.deleted = true;
    }

    public void unDelete(){
        this.deleted = false;
    }
}
