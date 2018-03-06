package com.arloid.alarmcall.service;

import com.arloid.alarmcall.entity.Call;
import org.springframework.data.domain.Page;

public interface CallService {
    Page<Call> findAll(int size, int page);

    Call save(Call call);

    Page<Call> findByCallNumberId(long callNumberId, int page, int size);
}
