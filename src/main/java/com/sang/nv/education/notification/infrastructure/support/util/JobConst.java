package com.sang.nv.education.notification.infrastructure.support.util;

public interface JobConst {
    String SEND_EVENT_TASK = "send-event-task";

    Integer TIME_RE_RUN_TASK_DEAD = 15; // (realtime = TIME_RE_RUN_TASK_DEAD * 4)
    Integer MAX_RETRIES_TASK = 3;
    Integer RETRY_DELAY = 60;
    Integer SEND_DELAY = 5;
}
