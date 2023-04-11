package com.sang.nv.education.exam.application.dto.request;

import com.sang.commonmodel.dto.request.BaseSearchRequest;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class RoomSearchRequest extends BaseSearchRequest {
    List<String> subjectIds;
}
