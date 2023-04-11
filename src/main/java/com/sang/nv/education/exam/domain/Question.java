package com.sang.nv.education.exam.domain;

import com.sang.commonutil.IdUtils;
import com.sang.nv.education.exam.domain.command.AnswerCreateOrUpdateCmd;
import com.sang.nv.education.exam.domain.command.QuestionCreateCmd;
import com.sang.nv.education.exam.domain.command.QuestionUpdateCmd;
import com.sang.nv.education.exam.infrastructure.persistence.entity.QuestionEntity;
import com.sang.nv.education.exam.infrastructure.support.enums.QuestionLevel;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Setter(AccessLevel.PRIVATE)
@Getter
public class Question {
    String id;
    String groupId;
    String subjectId;
    String title;
    Boolean deleted;
    QuestionLevel level;
    List<Answer> answers;


    public Question(QuestionCreateCmd cmd){
        this.id = IdUtils.nextId();
        this.title = cmd.getTitle();
        this.deleted = Boolean.FALSE;
        this.groupId = cmd.getGroupId();
        this.answers = new ArrayList<>();
        this.subjectId = cmd.getSubjectId();
        this.level = cmd.getQuestionLevel();
        if (!CollectionUtils.isEmpty(cmd.getAnswerCreateOrUpdateCmdList()))
        {
            this.answers = cmd.getAnswerCreateOrUpdateCmdList().stream().map(item -> new Answer(this.id, item)).collect(Collectors.toList());
        }
    }

    public Question(QuestionEntity cmd)
    {
        this.title = cmd.getTitle();
        this.groupId = cmd.getId();
        this.deleted = Boolean.FALSE;
    }

    public void update(QuestionUpdateCmd cmd)
    {
        this.title = cmd.getTitle();
        this.deleted = Boolean.FALSE;
        this.answers.forEach(Answer::deleted);
        this.level = cmd.getQuestionLevel();
        cmd.getAnswers().forEach(item -> {
            Optional<Answer> optionalAnswer = this.answers.stream().filter(answer -> Objects.equals(item.id, answer.id )).findFirst();
            if (optionalAnswer.isPresent())
            {
                Answer answer = optionalAnswer.get();
                answer.unDelete();
                answer.update(AnswerCreateOrUpdateCmd.builder().content(item.content).status(item.status).build());
            }
        });
        if (!CollectionUtils.isEmpty(cmd.getExtraAnswer()))
        {
            cmd.getExtraAnswer().forEach(item -> {
                this.answers.add(new Answer(this.id, item));
            });
        }
    }

    public void deleted(){
        this.deleted = true;
    }

    public void unDelete(){
        this.deleted = false;
    }

    public void enrichAnswers(List<Answer> answers)
    {
        this.answers = answers;
    }
}
