package com.arloid.alarmcall.service;

import com.arloid.alarmcall.entity.CallNumber;
import org.springframework.data.domain.Page;

public interface CallNumberService {
    Page<CallNumber> findAll(int page, int size);

    CallNumber save(CallNumber callNumber);

    Page<CallNumber> findByClientId(long id, int page, int size);
}
