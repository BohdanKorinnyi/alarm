package com.arloid.alarmcall.service.impl;

import com.arloid.alarmcall.entity.Alarm;
import com.arloid.alarmcall.entity.Call;
import com.arloid.alarmcall.entity.CallNumber;
import com.arloid.alarmcall.entity.CallStatus;
import com.arloid.alarmcall.repository.CallRepository;
import com.arloid.alarmcall.service.*;
import com.google.common.collect.ImmutableSet;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.Set;

@Service
@AllArgsConstructor
public class CallServiceImpl implements CallService {
    private final CallRepository callRepository;
    private final TwilioService twilioService;
    private final AlarmService alarmService;
    private final CallNumberService callNumberService;
    private final CallStatusService callStatusService;

    private static final Set<com.twilio.rest.api.v2010.account.Call.Status> END_CALL = ImmutableSet.of(
            com.twilio.rest.api.v2010.account.Call.Status.BUSY,
            com.twilio.rest.api.v2010.account.Call.Status.NO_ANSWER,
            com.twilio.rest.api.v2010.account.Call.Status.CANCELED,
            com.twilio.rest.api.v2010.account.Call.Status.FAILED
    );

    @Override
    public Page<Call> findAll(int size, int page) {
        return callRepository.findAll(createPageable(page, size));
    }

    @Override
    public Page<Call> findByCallNumberId(long callNumberId, int page, int size) {
        return callRepository.findByCallNumberId(callNumberId, createPageable(page, size));
    }

    @Override
    public void makeByClientId(long clientId) {
        CallNumber number = callNumberService.findByClientId(clientId);
        Alarm alarm = alarmService.findByClientId(clientId);
        Call call = new Call();
        call.setAlarm(alarm);
        call.setCallNumber(number);
        call.setCallStatus(callStatusService.findByName("created"));
        callRepository.save(call);
        com.twilio.rest.api.v2010.account.Call twilioCall = twilioService.makeCall(number.getNumber(), clientId);
        call.setProviderId(twilioCall.getSid());
        callRepository.save(call);
        CallStatusFetcher.add(twilioCall.getSid());
    }

    @Override
    public void update(com.twilio.rest.api.v2010.account.Call call) {
        Call callToUpdate = callRepository.findByProviderId(call.getSid());
        CallStatus status = callStatusService.findByName(call.getStatus().toString());
        callToUpdate.setCallStatus(status);
        callToUpdate.setDuration(StringUtils.isEmpty(call.getDuration()) ? null : Integer.parseInt(call.getDuration()));
        callToUpdate.setCost(call.getPrice());
        callToUpdate.setUpdated(new Date());
        callRepository.save(callToUpdate);
        if (END_CALL.contains(call.getStatus())) {
            CallStatusFetcher.remove(call.getSid());
        }
    }

    private Pageable createPageable(int page, int size) {
        return new PageRequest(--page, size);
    }
}
