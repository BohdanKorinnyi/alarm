package com.arloid.alarmcall.service;

import com.arloid.alarmcall.dto.RegistrationPhoneDto;
import com.arloid.alarmcall.entity.CallNumber;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CallNumberService {
  Page<CallNumber> findAll(Pageable pageable);

  void save(RegistrationPhoneDto phone, long clientId);

  CallNumber findByClientId(long id);
}
