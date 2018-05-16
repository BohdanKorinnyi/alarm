package com.arloid.alarmcall.service;

import com.arloid.alarmcall.dao.UCallStatisticRepository;
import com.arloid.alarmcall.dao.UCountryStatisticRepository;
import com.arloid.alarmcall.entity.ui.CallStatistic;
import com.arloid.alarmcall.entity.ui.CountryStatistic;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UCallStatisticServiceImpl implements UCallStatisticService {
  private final UCallStatisticRepository uCallStatisticRepository;
  private final UCountryStatisticRepository uCountryStatisticRepository;

  @Override
  public CallStatistic getCallStatistic() {
    return uCallStatisticRepository.getCallStatistic();
  }

  @Override
  public List<CountryStatistic> getCountryStatistic() {
    return uCountryStatisticRepository.getCountryStatistic();
  }
}
