package com.arloid.alarmcall.service;

import com.arloid.alarmcall.dao.UCallRepository;
import com.arloid.alarmcall.dao.UCallStatisticRepository;
import com.arloid.alarmcall.entity.ui.Call;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UCallServiceImpl implements UCallService {
  private final UCallRepository uCallRepository;
  private final UCallStatisticRepository uCallStatisticRepository;

  @Override
  public Page<Call> find(Pageable pageable) {
    return uCallRepository.find(pageable);
  }

  @Override
  public Page<Call> findByNumber(String number, Pageable pageable) {
    return uCallRepository.findByNumber(number.trim(), pageable);
  }
}
