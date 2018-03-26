package com.arloid.alarmcall.service.impl;

import com.arloid.alarmcall.service.CallService;
import com.twilio.rest.api.v2010.account.Call;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.concurrent.ConcurrentLinkedQueue;

@Slf4j
@Component
@AllArgsConstructor
public class CallStatusFetcher {
    private static ConcurrentLinkedQueue<String> CALL_SIDS = new ConcurrentLinkedQueue<>();
    private final CallService callService;

    @SneakyThrows
    @Scheduled(fixedRate = 2000L)
    public void fetchStatus() {
        if (CollectionUtils.isEmpty(CALL_SIDS)) {
            log.info("No calls for update");
            return;
        }
        log.info("Getting status for ids {}", CALL_SIDS);
        CALL_SIDS.forEach(id -> {
            Call call = Call.fetcher(id).fetch();
            callService.update(call);
        });
    }

    public static void add(String callId) {
        CALL_SIDS.add(callId);
    }

    public static void remove(String callId) {
        CALL_SIDS.remove(callId);
    }
}
