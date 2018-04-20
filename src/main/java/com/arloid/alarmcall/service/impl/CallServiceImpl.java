package com.arloid.alarmcall.service.impl;

import com.arloid.alarmcall.entity.Alarm;
import com.arloid.alarmcall.entity.AlarmCall;
import com.arloid.alarmcall.entity.CallNumber;
import com.arloid.alarmcall.repository.CallRepository;
import com.arloid.alarmcall.service.*;
import com.twilio.rest.api.v2010.account.Call;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.function.Function;

import static java.lang.Integer.parseInt;
import static java.util.Objects.isNull;
import static org.springframework.util.StringUtils.isEmpty;

@Service
@AllArgsConstructor
public class CallServiceImpl implements CallService {
  private static final int MAX_ATTEMPTS = 4;

  private final CallRepository callRepository;
  private final TwilioService twilioService;
  private final AlarmService alarmService;
  private final CallNumberService callNumberService;
  private final CallStatusService callStatusService;

  @Override
  public Page<AlarmCall> findAll(Pageable pageable) {
    return callRepository.findAll(pageable);
  }

  @Override
  public Page<AlarmCall> findByCallNumberId(Pageable pageable, long callNumberId) {
    return callRepository.findByCallNumberId(callNumberId, pageable);
  }

  @Override
  public void makeByClientId(long clientId) {
    CallNumber number = callNumberService.findByClientId(clientId);
    Alarm alarm = alarmService.findByClientId(clientId);
    AlarmCall alarmCall = new AlarmCall();
    alarmCall.setAlarm(alarm);
    alarmCall.setCallNumber(number);
    alarmCall.setCallStatus(callStatusService.findByName("created"));
    callRepository.save(alarmCall);
    Call twilioCall = twilioService.makeCall(number.getNumber(), clientId);
    alarmCall.setProviderId(twilioCall.getSid());
    callRepository.save(alarmCall);
    CallStatusFetcher.add(twilioCall.getSid());
  }

  @Override
  public void makeByProviderId(String providerId) {
    Function<AlarmCall, Long> parentIdFunction =
        alarmCall -> isNull(alarmCall.getParentId()) ? alarmCall.getId() : alarmCall.getParentId();
    AlarmCall alarmCall = callRepository.findByProviderId(providerId);
    if (alarmCall.getCallStatus().equals(callStatusService.findByName("completed"))) {
      Call call = Call.fetcher(providerId).fetch();
      callRepository.save(updateCall(call, alarmCall));
    }
    Integer attempt = callRepository.countByParentId(parentIdFunction.apply(alarmCall));
    if (MAX_ATTEMPTS > attempt) {
      CallNumber callNumber = alarmCall.getCallNumber();
      AlarmCall newAlarmCall = new AlarmCall();
      newAlarmCall.setAlarm(alarmCall.getAlarm());
      newAlarmCall.setCallNumber(callNumber);
      newAlarmCall.setCallStatus(callStatusService.findByName("created"));
      newAlarmCall.setParentId(parentIdFunction.apply(alarmCall));
      callRepository.save(newAlarmCall);
      Call twilioCall =
          twilioService.makeCall(callNumber.getNumber(), callNumber.getClient().getId());
      newAlarmCall.setProviderId(twilioCall.getSid());
      callRepository.save(newAlarmCall);
      CallStatusFetcher.add(twilioCall.getSid());
    }
  }

  @Override
  public void update(Call call) {
    AlarmCall alarmAlarmCall = callRepository.findByProviderId(call.getSid());
    callRepository.save(updateCall(call, alarmAlarmCall));
  }

  private AlarmCall updateCall(Call call, AlarmCall alarmAlarmCall) {
    alarmAlarmCall.setCallStatus(callStatusService.findByName(call.getStatus().toString()));
    alarmAlarmCall.setDuration(isEmpty(call.getDuration()) ? null : parseInt(call.getDuration()));
    alarmAlarmCall.setCost(call.getPrice());
    alarmAlarmCall.setUpdated(new Date());
    return alarmAlarmCall;
  }
}
