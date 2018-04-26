package com.arloid.alarmcall.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CallStatisticDto {
  private final long amount;
  private final double cost;
}
