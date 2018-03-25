package com.arloid.alarmcall.service.impl;

import com.arloid.alarmcall.entity.Alarm;
import com.arloid.alarmcall.entity.Call;
import com.arloid.alarmcall.entity.CallNumber;
import com.arloid.alarmcall.repository.CallRepository;
import com.arloid.alarmcall.service.AlarmService;
import com.arloid.alarmcall.service.CallNumberService;
import com.arloid.alarmcall.service.CallService;
import com.arloid.alarmcall.service.TwilioService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CallServiceImpl implements CallService {
    private final CallRepository callRepository;
    private final TwilioService twilioService;
    private final AlarmService alarmService;
    private final CallNumberService callNumberService;

    @Override
    public Page<Call> findAll(int size, int page) {
        return callRepository.findAll(createPageable(page, size));
    }

    @Override
    public Page<Call> findByCallNumberId(long callNumberId, int page, int size) {
        return callRepository.findByCallNumberId(callNumberId, createPageable(page, size));
    }

    @Override
    public Call makeByClientId(long clientId) {
        CallNumber number = callNumberService.findByClientId(clientId);
        Alarm alarm = alarmService.findByClientId(clientId);
        String callSid = twilioService.makeCall(number.getNumber(), alarm.getNameRecord());
        return null;
    }

    @Override
    public Call makeByPhoneNumberId(long phoneNumberId) {
        CallNumber number = callNumberService.findById(phoneNumberId);
        Alarm alarm = alarmService.findByClientId(number.getClient().getId());
        return null;
    }

    private Pageable createPageable(int page, int size) {
        return new PageRequest(--page, size);
    }
}
