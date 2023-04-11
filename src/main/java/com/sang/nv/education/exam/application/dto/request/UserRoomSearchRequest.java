package com.sang.nv.education.exam.application.dto.request;

import com.sang.commonmodel.dto.request.BaseSearchRequest;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class UserRoomSearchRequest extends BaseSearchRequest {

    List<String> roleIds;
    List<String> userIds;
}
