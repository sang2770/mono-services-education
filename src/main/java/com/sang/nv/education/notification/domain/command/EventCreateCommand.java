package com.sang.nv.education.notification.domain.command;

import com.sang.nv.education.notification.infrastructure.support.enums.EventEffectType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventCreateCommand {


    private String title;



    private EventEffectType effectType;

    private String attachedLink;

    private String content;

    private String note;


    private List<String> fileIds;

    private String issuedUserId;
}
