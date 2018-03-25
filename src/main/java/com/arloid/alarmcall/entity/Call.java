package com.arloid.alarmcall.entity;

import lombok.Data;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
public class Call {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private CallNumber callNumber;

    @ManyToOne
    private Alarm alarm;

    @ManyToOne
    private CallReason callReason;

    @ManyToOne
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private CallStatus callStatus;

    @Column(nullable = false, insertable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date creation;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date updated;

    @Column(nullable = false)
    private String provider;

    @Column(name = "listened_full", nullable = false)
    private Boolean fullyListened;

    @Column
    private Integer duration;

    @Column
    private BigDecimal cost;
}
