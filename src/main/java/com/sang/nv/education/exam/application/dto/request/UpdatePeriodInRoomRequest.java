package com.sang.nv.education.exam.application.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class UpdatePeriodInRoomRequest {
    List<String> periodIds;
}
