package com.arloid.alarmcall.entity.ui;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public final class Client {
  @Id private Long id;
  @Column private String firstName;
  @Column private String lastName;
  @Column private Boolean proof;
  @Column private String externalId;
  @Column private LocalDateTime registered;
  @Column private LocalDateTime updated;
}
