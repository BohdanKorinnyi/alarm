package com.arloid.alarmcall.dao;

import com.arloid.alarmcall.entity.ui.CallStatistic;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

public interface UCallStatisticRepository extends Repository<CallStatistic, Long> {
  @Query(nativeQuery = true, value = "SELECT count(id), sum(cost) FROM call")
  CallStatistic getCallStatistic();
}
