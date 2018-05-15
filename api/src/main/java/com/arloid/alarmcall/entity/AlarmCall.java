package com.arloid.alarmcall.entity;

import com.twilio.rest.api.v2010.account.Call;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

import static java.lang.Integer.parseInt;

@Data
@Entity
@NoArgsConstructor
@Table(name = "call")
public class AlarmCall {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToOne private CallNumber callNumber;
  @OneToOne private Alarm alarm;

  @ManyToOne
  @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
  private CallStatus callStatus;

  @Column(nullable = false, insertable = false, updatable = false)
  @Temporal(TemporalType.TIMESTAMP)
  private Date creation;

  @Column
  @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
  @Temporal(TemporalType.TIMESTAMP)
  private Date updated;

  @Column(nullable = false)
  private String provider;

  @Column(name = "listened_full", nullable = false)
  private Boolean fullyListened;

  @Column private Integer duration;
  @Column private BigDecimal cost;
  @Column private String providerId;
  @Column private Long parentId;

  public AlarmCall(CallNumber callNumber, CallStatus callStatus, Alarm alarm, long parentId) {
    this.callNumber = callNumber;
    this.callStatus = callStatus;
    this.parentId = parentId;
    this.alarm = alarm;
  }

  public AlarmCall copy(Call call, CallStatus callStatus) {
    copy(call);
    setCallStatus(callStatus);
    return this;
  }

  public AlarmCall copy(Call call) {
    setDuration(StringUtils.isEmpty(call.getDuration()) ? null : parseInt(call.getDuration()));
    setCost(call.getPrice());
    setUpdated(new Date());
    return this;
  }
}
