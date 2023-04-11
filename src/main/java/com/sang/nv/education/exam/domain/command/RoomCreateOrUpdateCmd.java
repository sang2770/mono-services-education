package com.sang.nv.education.exam.domain.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoomCreateOrUpdateCmd {
    String name;
    String subjectId;
    String code;
    String description;
    String subjectName;


    List<String> userIds;
}
