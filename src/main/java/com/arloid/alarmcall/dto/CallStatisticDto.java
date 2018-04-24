package com.arloid.alarmcall.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CallStatisticDto {
  private long amount;
  private double cost;
}
