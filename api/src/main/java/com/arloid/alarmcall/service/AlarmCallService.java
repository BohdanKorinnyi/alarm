package com.arloid.alarmcall.service;

import com.arloid.alarmcall.dto.CallStatisticDto;
import com.arloid.alarmcall.entity.AlarmCall;
import com.arloid.alarmcall.entity.CallStatus;
import com.twilio.rest.api.v2010.account.Call;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public interface AlarmCallService {
  void update(Call call);

  void complete(long clientId);

  AlarmCall findByProviderId(String twilioId);

  Page<AlarmCall> findAll(Pageable pageable);

  Page<AlarmCall> findByCallNumberId(Pageable pageable, long callNumberId);

  void makeByClientId(long clientId);

  void makeByProviderId(String providerId);

  CallStatisticDto getStatisticByStatus(CallStatus status);

  default double calculateCost(List<AlarmCall> calls) {
    return calls
        .stream()
        .map(AlarmCall::getCost)
        .filter(Objects::nonNull)
        .mapToDouble(BigDecimal::doubleValue)
        .sum();
  }
}
