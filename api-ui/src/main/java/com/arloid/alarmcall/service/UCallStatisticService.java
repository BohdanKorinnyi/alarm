package com.arloid.alarmcall.service;

import com.arloid.alarmcall.entity.ui.CallStatistic;
import com.arloid.alarmcall.entity.ui.CountryStatistic;

import java.util.List;

public interface UCallStatisticService {
  CallStatistic getCallStatistic();

  List<CountryStatistic> getCountryStatistic();
}
