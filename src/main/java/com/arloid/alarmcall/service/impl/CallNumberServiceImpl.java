package com.arloid.alarmcall.service.impl;

import com.arloid.alarmcall.dto.RegistrationPhoneDto;
import com.arloid.alarmcall.entity.CallNumber;
import com.arloid.alarmcall.repository.CallNumberRepository;
import com.arloid.alarmcall.service.CallNumberService;
import com.arloid.alarmcall.service.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CallNumberServiceImpl implements CallNumberService {
  private final CallNumberRepository callNumberRepository;
  private final ClientService clientService;

  @Override
  public Page<CallNumber> findAll(Pageable pageable) {
    return callNumberRepository.findAll(pageable);
  }

  @Override
  public void save(RegistrationPhoneDto phone, long clientId) {
    clientService
        .findOne(clientId)
        .ifPresent(
            client -> {
              CallNumber callNumber = new CallNumber();
              callNumber.setClient(client);
              callNumber.setNumber(phone.getNumber());
              callNumber.setActive(true);
              callNumberRepository.save(callNumber);
            });
  }

  @Override
  public CallNumber findByClientId(long clientId) {
    return callNumberRepository.findByClientId(clientId);
  }
}
