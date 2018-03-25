package com.arloid.alarmcall.service;

import com.arloid.alarmcall.entity.Call;
import org.springframework.data.domain.Page;

public interface CallService {
    Page<Call> findAll(int size, int page);

    Page<Call> findByCallNumberId(long callNumberId, int page, int size);

    Call makeByClientId(long clientId);

    Call makeByPhoneNumberId(long phoneNumberId);
}
