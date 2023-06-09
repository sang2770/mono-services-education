package com.sang.nv.education.exam.domain;

import com.sang.commonutil.IdUtils;
import com.sang.nv.education.exam.domain.command.AnswerCreateOrUpdateCmd;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Objects;

@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Setter(AccessLevel.PRIVATE)
@Getter
public class Answer {
    String id;
    String content;
    Boolean status;
    String questionId;
    Boolean deleted;

    public Answer(String questionId, AnswerCreateOrUpdateCmd cmd){
        this.id = IdUtils.nextId();
        this.content = cmd.getContent();
        this.questionId = questionId;
        this.status = Objects.nonNull(cmd.getStatus()) ? cmd.getStatus() : Boolean.FALSE;
        this.deleted = Boolean.FALSE;
    }

    public void update(AnswerCreateOrUpdateCmd cmd)
    {
        this.content = cmd.getContent();
        this.status = Objects.nonNull(cmd.getStatus()) ? cmd.getStatus() : Boolean.FALSE;
        this.deleted = Boolean.FALSE;
    }

    public void deleted(){
        this.deleted = true;
    }

    public void unDelete(){
        this.deleted = false;
    }
}
