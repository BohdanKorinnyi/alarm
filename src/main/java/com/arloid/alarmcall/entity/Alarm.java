package com.arloid.alarmcall.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Alarm implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @JsonIgnore
  @OneToOne(fetch = FetchType.LAZY)
  private Client client;

  @Column(nullable = false)
  private String nameRecord;

  @Column(nullable = false)
  private String addressRecord;

  @Column(nullable = false)
  private AlarmRecordType type;

  @Column private Boolean active;

  @Column(nullable = false, insertable = false, updatable = false)
  @Temporal(TemporalType.TIMESTAMP)
  private Date creation;

  @OneToOne(fetch = FetchType.EAGER)
  private Language language;

  public enum AlarmRecordType {
    LINK,
    SPEECH;
  }
}
