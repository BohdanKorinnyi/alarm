package com.arloid.alarmcall.service;

import com.arloid.alarmcall.entity.CallReason;

import java.util.List;

public interface CallReasonService {
    CallReason findByName(String name);

    void save(CallReason reason);

    List<CallReason> findAll();
}
