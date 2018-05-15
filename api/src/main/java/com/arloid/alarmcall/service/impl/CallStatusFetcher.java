package com.arloid.alarmcall.service.impl;

import com.arloid.alarmcall.entity.AlarmCall;
import com.arloid.alarmcall.service.AlarmCallService;
import com.google.common.collect.ImmutableSet;
import com.twilio.rest.api.v2010.account.Call;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Slf4j
@Component
@AllArgsConstructor
public class CallStatusFetcher {
  private static final Map<Long, String> calls = new ConcurrentHashMap<>();
  private static final Set<Call.Status> UNSUCCESSFUL_CALL_STATUSES =
      ImmutableSet.of(
          Call.Status.BUSY,
          Call.Status.NO_ANSWER,
          Call.Status.CANCELED,
          Call.Status.FAILED);

  private final AlarmCallService alarmCallService;

  public static void add(long clientId, String callId) {
    calls.put(clientId, callId);
  }

  static void remove(long clientId) {
    calls.remove(clientId);
  }

  static String getCallSid(long clientId) {
    return calls.get(clientId);
  }

  @SneakyThrows
  @Scheduled(fixedRate = 4000L)
  public void fetchStatus() {
    calls.forEach(this::updateStatus);
  }

  private void updateStatus(long clientId, String sid) {
    Call call = Call.fetcher(sid).fetch();
    alarmCallService.update(call);
    log.info(
        "Call status to client {}, sid {}, fetched status {}", clientId, sid, call.getStatus());
    if (UNSUCCESSFUL_CALL_STATUSES.contains(call.getStatus())) {
      calls.remove(clientId);
      log.info("Call {} is not success, status is {}", sid, call.getStatus());
      alarmCallService.makeByProviderId(sid);
    } else if (Call.Status.COMPLETED.equals(call.getStatus())) {
      processCompletedCall(call, clientId);
    }
  }

  private void processCompletedCall(Call call, long clientId) {
    AlarmCall alarmCall = alarmCallService.findByProviderId(call.getSid());
    if (isNull(alarmCall.getFullyListened())
        || (nonNull(alarmCall.getFullyListened()) && !alarmCall.getFullyListened())) {
      log.info("Call {} completed but not fully listened, calling again...", call.getSid());
      calls.remove(clientId);
      alarmCallService.makeByProviderId(call.getSid());
    } else {
      log.info("Call {} completed and fully listened!", call.getSid());
      calls.remove(clientId);
    }
  }
}
