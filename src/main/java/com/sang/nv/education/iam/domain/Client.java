package com.sang.nv.education.iam.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sang.commonmodel.domain.AuditableDomain;
import com.sang.commonutil.IdUtils;
import com.sang.commonutil.RandomUtils;
import com.sang.nv.education.iamdomain.command.ClientCreateOrUpdateCmd;
import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Setter(AccessLevel.PRIVATE)
@Getter
public class Client extends AuditableDomain {

    private String id;

    private String name;

    @JsonIgnore
    private String secret;


    private Boolean deleted;
    public Client(ClientCreateOrUpdateCmd cmd) {
        this.id = IdUtils.nextId();
        this.name = cmd.getName();
        this.secret = RandomUtils.generateSecret();
        this.deleted = false;
    }

    public void update(ClientCreateOrUpdateCmd cmd) {
        this.name = cmd.getName();
        this.deleted = false;
    }

    public void delete() {
        this.deleted = true;
    }

}
