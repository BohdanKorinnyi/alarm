package com.arloid.alarmcall.service;

import com.arloid.alarmcall.dto.RegistrationDto;
import com.arloid.alarmcall.entity.CallNumber;
import org.springframework.data.domain.Page;

public interface CallNumberService {
    CallNumber findById(long callNumberId);

    Page<CallNumber> findAll(int page, int size);

    CallNumber save(RegistrationDto.Phone phone, long clientId);

    CallNumber findByClientId(long id);
}
