package com.arloid.alarmcall.entity.ui;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Entity
public class CallStatistic {
  @Id private long count;
  private double sum;
}
