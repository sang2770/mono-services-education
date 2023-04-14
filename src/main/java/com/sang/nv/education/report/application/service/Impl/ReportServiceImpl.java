package com.sang.nv.education.report.application.service.Impl;

import com.sang.nv.education.exam.application.service.ExamService;
import com.sang.nv.education.exam.application.service.PeriodService;
import com.sang.nv.education.exam.application.service.RoomService;
import com.sang.nv.education.exam.domain.Period;
import com.sang.nv.education.exam.domain.Room;
import com.sang.nv.education.iam.application.service.UserService;
import com.sang.nv.education.iam.domain.User;
import com.sang.nv.education.report.application.dto.request.ReportGeneralRequest;
import com.sang.nv.education.report.application.service.ReportService;
import com.sang.nv.education.report.domain.GeneralReport;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class ReportServiceImpl implements ReportService {
    private final RoomService roomService;
    private final UserService userService;
    private final ExamService examService;
    private final PeriodService periodService;
    @Override
    public GeneralReport generalReport(ReportGeneralRequest request) {
        List<Room> rooms = new ArrayList<>();
        if (!CollectionUtils.isEmpty(request.getRoomIds()))
        {
            rooms.addAll(roomService.getByIds(request.getRoomIds()));
        }else{
            rooms.addAll(roomService.getAll());
        }
        return GeneralReport.builder()
                .numberRoom(rooms.size())
                .numberPeriod(this.periodService.count(request.getRoomIds()))
                .numberExam()
                .build();
    }
}
