package com.arloid.alarmcall.service;

import com.arloid.alarmcall.entity.CallStatus;

import java.util.List;

public interface CallStatusService {
    CallStatus findByName(String name);

    void save(CallStatus status);

    List<CallStatus> findAll();
}
