package com.arloid.alarmcall.entity.ui;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Entity
public class CountryStatistic {
  @Id private long count;
  private String country;
}
