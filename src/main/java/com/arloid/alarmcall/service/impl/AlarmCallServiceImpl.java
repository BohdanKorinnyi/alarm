package com.arloid.alarmcall.service.impl;

import com.arloid.alarmcall.dto.CallStatisticDto;
import com.arloid.alarmcall.entity.Alarm;
import com.arloid.alarmcall.entity.AlarmCall;
import com.arloid.alarmcall.entity.CallNumber;
import com.arloid.alarmcall.entity.CallStatus;
import com.arloid.alarmcall.repository.AlarmCallRepository;
import com.arloid.alarmcall.service.*;
import com.twilio.rest.api.v2010.account.Call;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

import static com.arloid.alarmcall.service.impl.CallStatusFetcher.isClientCallAvailable;
import static java.lang.Integer.parseInt;
import static java.util.Objects.isNull;
import static org.springframework.util.StringUtils.isEmpty;

@Slf4j
@Service
@AllArgsConstructor
public class AlarmCallServiceImpl implements AlarmCallService {
  private static final int MAX_ATTEMPTS = 4;

  private final AlarmCallRepository alarmCallRepository;
  private final TwilioService twilioService;
  private final AlarmService alarmService;
  private final CallNumberService callNumberService;
  private final CallStatusService callStatusService;

  @Override
  public CallStatisticDto getStatisticByStatus(CallStatus status) {
    List<AlarmCall> calls = alarmCallRepository.findByCallStatus(status);
    return new CallStatisticDto(
        calls.size(),
        calls
            .stream()
            .map(AlarmCall::getCost)
            .filter(Objects::nonNull)
            .mapToDouble(BigDecimal::doubleValue)
            .sum());
  }

  @Override
  public Page<AlarmCall> findAll(Pageable pageable) {
    return alarmCallRepository.findAll(pageable);
  }

  @Override
  public Page<AlarmCall> findByCallNumberId(Pageable pageable, long callNumberId) {
    return alarmCallRepository.findByCallNumberId(callNumberId, pageable);
  }

  @Override
  public void makeByClientId(long clientId) {
    log.info("Try to call client {}", clientId);
    if (isClientCallAvailable(clientId)) {
      log.info("System has been calling to the client {}", clientId);
      throw new RuntimeException("System has been calling to the client");
    }
    CallNumber number = callNumberService.findByClientId(clientId);
    Alarm alarm = alarmService.findByClientId(clientId);
    AlarmCall alarmCall = new AlarmCall();
    alarmCall.setAlarm(alarm);
    alarmCall.setCallNumber(number);
    alarmCall.setCallStatus(callStatusService.findByName("created"));
    alarmCallRepository.save(alarmCall);
    Call twilioCall = twilioService.makeCall(number.getNumber(), clientId);
    alarmCall.setProviderId(twilioCall.getSid());
    alarmCallRepository.save(alarmCall);
    CallStatusFetcher.add(clientId, twilioCall.getSid());
  }

  @Override
  public void makeByProviderId(String providerId) {
    Function<AlarmCall, Long> parentIdFunction =
        alarmCall -> isNull(alarmCall.getParentId()) ? alarmCall.getId() : alarmCall.getParentId();
    AlarmCall alarmCall = alarmCallRepository.findByProviderId(providerId);
    if (alarmCall.getCallStatus().equals(callStatusService.findByName("completed"))) {
      Call call = Call.fetcher(providerId).fetch();
      alarmCallRepository.save(updateCall(call, alarmCall));
    }
    Integer attempt = alarmCallRepository.countByParentId(parentIdFunction.apply(alarmCall));
    if (MAX_ATTEMPTS > attempt) {
      CallNumber callNumber = alarmCall.getCallNumber();
      AlarmCall newAlarmCall = new AlarmCall();
      newAlarmCall.setAlarm(alarmCall.getAlarm());
      newAlarmCall.setCallNumber(callNumber);
      newAlarmCall.setCallStatus(callStatusService.findByName("created"));
      newAlarmCall.setParentId(parentIdFunction.apply(alarmCall));
      alarmCallRepository.save(newAlarmCall);
      Call twilioCall =
          twilioService.makeCall(callNumber.getNumber(), callNumber.getClient().getId());
      newAlarmCall.setProviderId(twilioCall.getSid());
      alarmCallRepository.save(newAlarmCall);
      CallStatusFetcher.add(alarmCall.getId(), twilioCall.getSid());
    }
  }

  @Override
  public void update(Call call) {
    AlarmCall alarmAlarmCall = alarmCallRepository.findByProviderId(call.getSid());
    alarmCallRepository.save(updateCall(call, alarmAlarmCall));
  }

  @Override
  public void update(long clientId) {
    log.info("Update completed call by client id " + clientId);
    String callSid = CallStatusFetcher.getCallSid(clientId);
    AlarmCall alarmCall = alarmCallRepository.findByProviderId(callSid);
    alarmCall.setFullyListened(true);
    alarmCall.setUpdated(new Date());
    alarmCallRepository.save(alarmCall);
    CallStatusFetcher.remove(clientId);
  }

  private AlarmCall updateCall(Call call, AlarmCall alarmAlarmCall) {
    alarmAlarmCall.setCallStatus(callStatusService.findByName(call.getStatus().toString()));
    alarmAlarmCall.setDuration(isEmpty(call.getDuration()) ? null : parseInt(call.getDuration()));
    alarmAlarmCall.setCost(call.getPrice());
    alarmAlarmCall.setUpdated(new Date());
    return alarmAlarmCall;
  }
}
