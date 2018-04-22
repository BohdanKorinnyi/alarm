package com.arloid.alarmcall.service;

import com.arloid.alarmcall.entity.CallStatus;

import java.util.List;

public interface CallStatusService {
  List<CallStatus> findAll();

  CallStatus findByName(String name);
}
