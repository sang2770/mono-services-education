package com.sang.nv.education.exam.application.mapper;


import com.sang.commonmodel.mapper.BaseAutoMapper;
import com.sang.nv.education.exam.application.dto.request.ExamSearchRequest;
import com.sang.nv.education.exam.application.dto.request.QuestionSearchRequest;
import com.sang.nv.education.exam.application.dto.request.RoomSearchRequest;
import com.sang.nv.education.exam.application.dto.request.UserRoomSearchRequest;
import com.sang.nv.education.exam.infrastructure.persistence.query.ExamSearchQuery;
import com.sang.nv.education.exam.infrastructure.persistence.query.QuestionSearchQuery;
import com.sang.nv.education.exam.infrastructure.persistence.query.RoomSearchQuery;
import com.sang.nv.education.exam.infrastructure.persistence.query.UserRoomSearchQuery;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ExamAutoMapperQuery extends BaseAutoMapper {
    ExamSearchQuery from(ExamSearchRequest request);
    RoomSearchQuery from(RoomSearchRequest request);
    QuestionSearchQuery from(QuestionSearchRequest request);
    UserRoomSearchQuery from(UserRoomSearchRequest request);

}
