package com.sang.nv.education.exam.application.dto.request.room;

import com.sang.commonmodel.dto.request.BaseSearchRequest;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class PeriodRoomSearchRequest extends BaseSearchRequest {

    List<String> ids;
}
