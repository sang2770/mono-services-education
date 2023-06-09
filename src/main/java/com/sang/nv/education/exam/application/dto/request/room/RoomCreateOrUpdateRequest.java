package com.sang.nv.education.exam.application.dto.request.room;

import com.sang.commonmodel.dto.request.Request;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class RoomCreateOrUpdateRequest extends Request {

    String name;
    String subjectId;
    String description;

}
