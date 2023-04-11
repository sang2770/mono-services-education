package com.sang.nv.education.exam.application.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.List;

@NoArgsConstructor
@Data
public class UpdateMemberInRoomRequest {
    @NotBlank(message = "GROUP_ID_REQUIRED")
    String roomId;
    List<String> memberIds;
}
