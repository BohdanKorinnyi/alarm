package com.arloid.alarmcall.service.impl;

import com.arloid.alarmcall.service.AlarmCallService;
import com.google.common.collect.ImmutableSet;
import com.twilio.rest.api.v2010.account.Call;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;

@Slf4j
@Component
@AllArgsConstructor
public class CallStatusFetcher {
  public static final Set<Call.Status> UNSUCCESSFUL_CALL_STATUSES =
      ImmutableSet.of(
          com.twilio.rest.api.v2010.account.Call.Status.BUSY,
          com.twilio.rest.api.v2010.account.Call.Status.NO_ANSWER,
          com.twilio.rest.api.v2010.account.Call.Status.CANCELED,
          com.twilio.rest.api.v2010.account.Call.Status.FAILED);
  private static ConcurrentLinkedQueue<String> callSids = new ConcurrentLinkedQueue<>();
  private final AlarmCallService alarmCallService;

  public static void add(String callId) {
    callSids.add(callId);
  }

  @SneakyThrows
  @Scheduled(fixedRate = 4000L)
  public void fetchStatus() {
    if (CollectionUtils.isEmpty(callSids)) {
      return;
    }
    callSids.forEach(
        id -> {
          Call call = Call.fetcher(id).fetch();
          alarmCallService.update(call);
          if (com.twilio.rest.api.v2010.account.Call.Status.COMPLETED.equals(call.getStatus())) {
            callSids.remove(call.getSid());
          } else if (UNSUCCESSFUL_CALL_STATUSES.contains(call.getStatus())) {
            callSids.remove(call.getSid());
            alarmCallService.makeByProviderId(call.getSid());
          }
        });
  }
}
