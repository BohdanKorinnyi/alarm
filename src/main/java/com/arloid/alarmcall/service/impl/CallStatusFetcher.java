package com.arloid.alarmcall.service.impl;

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

@Slf4j
@Component
@AllArgsConstructor
public class CallStatusFetcher {
  private static final Map<Long, String> calls = new ConcurrentHashMap<>();

  private static final Set<Call.Status> UNSUCCESSFUL_CALL_STATUSES =
      ImmutableSet.of(
          com.twilio.rest.api.v2010.account.Call.Status.BUSY,
          com.twilio.rest.api.v2010.account.Call.Status.NO_ANSWER,
          com.twilio.rest.api.v2010.account.Call.Status.CANCELED,
          com.twilio.rest.api.v2010.account.Call.Status.FAILED);
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

  static boolean isClientCallAvailable(long clientId) {
    return calls.containsKey(clientId);
  }

  @SneakyThrows
  @Scheduled(fixedRate = 4000L)
  public void fetchStatus() {
    calls.forEach(
        (clientId, sid) -> {
          Call call = Call.fetcher(sid).fetch();
          alarmCallService.update(call);
          if (UNSUCCESSFUL_CALL_STATUSES.contains(call.getStatus())) {
            calls.remove(clientId);
            alarmCallService.makeByProviderId(call.getSid());
          }
        });
  }
}
