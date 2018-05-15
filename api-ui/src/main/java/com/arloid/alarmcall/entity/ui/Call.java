package com.arloid.alarmcall.entity.ui;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "call")
public class Call {
  @Id private Long id;
  private String number;
}
