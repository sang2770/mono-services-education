package com.sang.nv.education.iam.application.dto.request.Classes;

import com.sang.commonmodel.dto.request.PagingRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClassSearchRequest extends PagingRequest {

    private List<String> ids;
    private List<String> departmentIds;
    private List<String> keyIds;
    private String keyword;
}
