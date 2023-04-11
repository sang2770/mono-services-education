package com.sang.nv.education.exam.web.rest;


import com.sang.commonmodel.dto.response.PagingResponse;
import com.sang.commonmodel.dto.response.Response;
import com.sang.nv.education.exam.application.dto.request.*;
import com.sang.nv.education.exam.application.dto.request.room.PeriodRoomSearchRequest;
import com.sang.nv.education.exam.domain.PeriodRoom;
import com.sang.nv.education.exam.domain.Room;
import com.sang.nv.education.exam.domain.UserRoom;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Room Resource")
@RequestMapping("/api")
public interface RoomResource {
    @ApiOperation(value = "Search Room")
    @GetMapping("/rooms")
    PagingResponse<Room> search(RoomSearchRequest request);

    @ApiOperation(value = "Create Room")
    @PostMapping("/rooms")
    Response<Room> create(@RequestBody RoomCreateOrUpdateRequest request);

    @ApiOperation(value = "Update Room")
    @PostMapping("/rooms/{id}/update")
    Response<Room> update(@PathVariable String id, @RequestBody RoomCreateOrUpdateRequest request);

    @ApiOperation(value = "Get Room by id")
    @GetMapping("/rooms/{id}")
    Response<Room> getById(@PathVariable String id);

    @ApiOperation(value = "Update Room")
    @PostMapping("/rooms/{id}/add-members")
    Response<Room> addMember(@PathVariable String id, @RequestBody UpdateMemberInRoomRequest request);


    @ApiOperation(value = "remove Member Room")
    @PostMapping("/rooms/{id}/remove-members")
    Response<Room> removeMember(@PathVariable String id, @RequestBody UpdateMemberInRoomRequest request);


    @ApiOperation(value = "remove exam Room")
    @PostMapping("/rooms/{id}/add-periods")
    Response<Room> addPeriod(@PathVariable String id, @RequestBody UpdatePeriodInRoomRequest request);


    @ApiOperation(value = "Update Room")
    @PostMapping("/rooms/{id}/remove-periods")
    Response<Room> removePeriod(@PathVariable String id, @RequestBody UpdatePeriodInRoomRequest request);

    @ApiOperation(value = "get User in room")
    @GetMapping("/rooms/{id}/users")
    PagingResponse<UserRoom> getUserInRoom(@PathVariable String id, UserRoomSearchRequest request);

    @ApiOperation(value = "get period in room")
    @GetMapping("/rooms/{id}/periods")
    PagingResponse<PeriodRoom> getPeriodInRoom(@PathVariable String id, PeriodRoomSearchRequest
            request);


    @ApiOperation(value = "Send exam to user Room")
    @PostMapping("/rooms/{id}/send-exam-to-user")
    Response<Void> sendExamToUser(@PathVariable String id, @RequestBody UserExamCreateRequest request);

//    For client
    @ApiOperation(value = "Get My Room")
    @GetMapping("/rooms/my-room")
    PagingResponse<Room> getMyRoom(RoomSearchRequest request);

}
