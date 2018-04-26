package com.arloid.alarmcall.service.impl;

import com.arloid.alarmcall.configuration.AlarmCallProperties;
import com.arloid.alarmcall.dto.CallStatisticDto;
import com.arloid.alarmcall.entity.AlarmCall;
import com.arloid.alarmcall.entity.CallStatus;
import com.arloid.alarmcall.repository.AlarmCallRepository;
import com.arloid.alarmcall.service.*;
import com.twilio.rest.api.v2010.account.Call;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.function.Function;

import static com.arloid.alarmcall.service.impl.CallStatusFetcher.getCallSid;
import static com.arloid.alarmcall.service.impl.CallStatusServiceImpl.COMPLETED;
import static com.arloid.alarmcall.service.impl.CallStatusServiceImpl.CREATED;
import static java.util.Objects.isNull;

@Slf4j
@Service
@RequiredArgsConstructor
public class AlarmCallServiceImpl implements AlarmCallService {
  private Function<AlarmCall, Long> parentId =
      call -> isNull(call.getParentId()) ? call.getId() : call.getParentId();

  private final AlarmService alarmService;
  private final TwilioService twilioService;
  private final CallNumberService callNumberService;
  private final CallStatusService callStatusService;
  private final AlarmCallRepository alarmCallRepository;
  private final AlarmCallProperties alarmCallProperties;

  @Override
  public CallStatisticDto getStatisticByStatus(CallStatus status) {
    List<AlarmCall> calls = alarmCallRepository.findByCallStatus(status);
    return new CallStatisticDto(calls.size(), calculateCost(calls));
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
    AlarmCall alarmCall = new AlarmCall();
    alarmCall.setAlarm(alarmService.findByClientId(clientId));
    alarmCall.setCallNumber(callNumberService.findByClientId(clientId));
    alarmCall.setCallStatus(callStatusService.findByName(CREATED));
    alarmCallRepository.save(alarmCall);
    log.info("Call to client {} was created with id {}", clientId, alarmCall.getId());
    Call twilioCall = twilioService.makeCall(alarmCall.getCallNumber().getNumber(), clientId);
    log.info(
        "Call {} to client {} was created on Twilio with sid {}",
        alarmCall.getId(),
        clientId,
        twilioCall.getSid());
    alarmCall.setProviderId(twilioCall.getSid());
    alarmCallRepository.save(alarmCall);
    CallStatusFetcher.add(clientId, twilioCall.getSid());
  }

  @Override
  public void makeByProviderId(String providerId) {
    log.info("Call to client by twilio id {}", providerId);
    AlarmCall alarmCall = alarmCallRepository.findByProviderId(providerId);
    if (alarmCall.getCallStatus().equals(callStatusService.findByName(COMPLETED))) {
      Call call = Call.fetcher(providerId).fetch();
      alarmCallRepository.save(
          alarmCall.copy(call, callStatusService.findByName(call.getStatus().toString())));
    }
    Integer attempt = alarmCallRepository.countByParentId(parentId.apply(alarmCall));
    if (alarmCallProperties.getMaxCallAttempts() > attempt) {
      log.info("Trying to call client by provider id {}, attempt {}", providerId, attempt + 1);
      retryCall(alarmCall);
    } else {
      log.info(
          "Were done all available attempts calling client {}",
          alarmCall.getCallNumber().getClient().getId());
      CallStatusFetcher.remove(alarmCall.getCallNumber().getClient().getId());
    }
  }

  @Override
  public void update(Call call) {
    AlarmCall alarmCall = alarmCallRepository.findByProviderId(call.getSid());
    alarmCallRepository.save(
        alarmCall.copy(call, callStatusService.findByName(call.getStatus().toString())));
  }

  @Override
  public void complete(long clientId) {
    String callSid = getCallSid(clientId);
    try {
      log.info("Call {} to client {} was listened to and completed", callSid, clientId);
      CallStatusFetcher.remove(clientId);
      log.info("Getting calls by provider id {}", callSid);
      AlarmCall alarmCall = alarmCallRepository.findByProviderId(callSid);
      alarmCall.setFullyListened(true);
      alarmCall.setUpdated(new Date());
      alarmCallRepository.save(alarmCall);
    } catch (Exception e) {
      log.error(
          "An error has occurred during updating call {} for client {}", callSid, clientId, e);
      CallStatusFetcher.remove(clientId);
    }
  }

  @Override
  public AlarmCall findByProviderId(String twilioId) {
    return alarmCallRepository.findByProviderId(twilioId);
  }

  private void retryCall(AlarmCall alarmCall) {
    AlarmCall newAlarmCall =
        new AlarmCall(
            alarmCall.getCallNumber(),
            callStatusService.findByName(CREATED),
            alarmCall.getAlarm(),
            parentId.apply(alarmCall));
    Long clientId = newAlarmCall.getCallNumber().getClient().getId();
    log.info("Retry call to client {} parent {}", clientId, newAlarmCall.getParentId());
    alarmCallRepository.save(newAlarmCall);
    Call twilioCall = twilioService.makeCall(alarmCall.getCallNumber().getNumber(), clientId);
    newAlarmCall.setProviderId(twilioCall.getSid());
    alarmCallRepository.save(newAlarmCall);
    CallStatusFetcher.add(alarmCall.getId(), twilioCall.getSid());
  }
}
