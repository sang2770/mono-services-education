package com.sang.nv.education.exam.web.rest;

import com.sang.commonmodel.dto.response.Response;
import com.sang.nv.education.exam.application.dto.request.UserExamCreateRequest;
import com.sang.nv.education.exam.application.dto.request.room.SendExamToUserRequest;
import com.sang.nv.education.exam.application.dto.response.UserExamResult;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;

public interface SocketResource {
    @MessageMapping("/room/{id}")
    @SendTo("/topic/room/{id}")
    Response<Boolean> sendExam(@DestinationVariable String id, @Payload SendExamToUserRequest request);

    @MessageMapping("/user-exams/{id}/test")
    @SendTo("/topic/user-exams/{id}/test")
    Response<UserExamResult> sendTest(@DestinationVariable String id, @Payload UserExamCreateRequest request);

}
