package com.sang.nv.education.iam.domain.query;

import com.sang.commonmodel.query.PagingQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
public class ClassSearchQuery extends PagingQuery {

    private List<String> ids;
    private List<String> departmentIds;
    private List<String> keyIds;
    private String keyword;

}
