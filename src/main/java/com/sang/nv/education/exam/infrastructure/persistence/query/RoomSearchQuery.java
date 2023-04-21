package com.sang.nv.education.exam.infrastructure.persistence.query;

import com.sang.commonmodel.query.PagingQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
public class RoomSearchQuery extends PagingQuery {
    List<String> subjectIds;
    List<String> userIds;
    List<String> ids;
}
