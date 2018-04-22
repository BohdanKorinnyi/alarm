package com.arloid.alarmcall.service;

import com.arloid.alarmcall.dto.CallStatisticDto;
import com.arloid.alarmcall.entity.AlarmCall;
import com.arloid.alarmcall.entity.CallStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AlarmCallService {
  CallStatisticDto getStatisticByStatus(CallStatus status);

  Page<AlarmCall> findAll(Pageable pageable);

  Page<AlarmCall> findByCallNumberId(Pageable pageable, long callNumberId);

  void makeByClientId(long clientId);

  void makeByProviderId(String providerId);

  void update(com.twilio.rest.api.v2010.account.Call call);
}
