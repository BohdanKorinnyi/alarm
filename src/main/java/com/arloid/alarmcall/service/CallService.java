package com.arloid.alarmcall.service;

import com.arloid.alarmcall.entity.Call;
import org.springframework.data.domain.Page;

public interface CallService {
    Page<Call> findAll(int size, int page);

    Page<Call> findByCallNumberId(long callNumberId, int page, int size);

    void makeByClientId(long clientId);

    void update(com.twilio.rest.api.v2010.account.Call call);
}
