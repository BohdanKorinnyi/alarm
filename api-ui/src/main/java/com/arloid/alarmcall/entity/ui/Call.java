package com.arloid.alarmcall.entity.ui;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Call {
  @Id private Long id;
  private String number;
  private String value;
  private Integer duration;
  private Double cost;
  private String name;
  private Integer parentId;
  private LocalDateTime creation;
  private LocalDateTime updated;
}
