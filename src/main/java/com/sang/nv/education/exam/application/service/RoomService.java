package com.sang.nv.education.exam.application.service;

import com.sang.commonmodel.dto.PageDTO;
import com.sang.nv.education.exam.application.dto.request.*;
import com.sang.nv.education.exam.application.dto.request.room.PeriodRoomSearchRequest;
import com.sang.nv.education.exam.application.dto.request.room.SendExamToUserRequest;
import com.sang.nv.education.exam.domain.PeriodRoom;
import com.sang.nv.education.exam.domain.Room;
import com.sang.nv.education.exam.domain.UserRoom;

public interface RoomService {
    /**
     * Create Room
     *
     * @param request RoomCreateRequest
     * @return Room
     */
    Room create(RoomCreateOrUpdateRequest request);

    /**
     * Update Room
     *
     * @param request RoomCreateOrUpdateRequest
     * @return Room
     */
    Room update(String id, RoomCreateOrUpdateRequest request);

    /**
     * Search Room
     *
     * @param request RoomSearchRequest
     * @return PageDTO<Room>
     */
    PageDTO<Room> search(RoomSearchRequest request);

    /**
     * Get detail Room
     *
     * @param id String
     * @return Room
     */
    Room getById(String id);

    Room addMemberInRoom(String id, UpdateMemberInRoomRequest request);
    Room removeMemberInRoom(String id, UpdateMemberInRoomRequest request);

    Room addPeriodInRoom(String id, UpdatePeriodInRoomRequest request);
    Room removePeriodInRoom(String id, UpdatePeriodInRoomRequest request);
    PageDTO<UserRoom> getUserInRoom(String id, UserRoomSearchRequest request);
    PageDTO<PeriodRoom> getPeriodInRoom(String id, PeriodRoomSearchRequest request);

    void sendExamToUser(String id, UserExamCreateRequest request);

    void sendExam(String id, SendExamToUserRequest request);

    /**
     * Search Room
     *
     * @param request RoomSearchRequest
     * @return PageDTO<Room>
     */
    PageDTO<Room> getMyRoom(RoomSearchRequest request);

}
