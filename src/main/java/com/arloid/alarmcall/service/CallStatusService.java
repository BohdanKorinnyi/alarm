package com.arloid.alarmcall.service;

import com.arloid.alarmcall.entity.CallStatus;

public interface CallStatusService {
  CallStatus findByName(String name);
}
