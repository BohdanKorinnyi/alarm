package com.arloid.alarmcall.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;

@Entity
public class Call implements Serializable {

    private static final long serialVersionUID = 3443583048152724965L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @Getter @Setter private Client client;

    @ManyToOne
    @Getter @Setter private CallNumber callNumber;

    @Column(nullable = false, insertable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @Getter private Date created;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    @Getter @Setter private Date updated;

    @Column(nullable = false)
    private String reason;

    @Column(nullable = false)
    private String provider;

    @Column(nullable = false)
    private String status;

    @Column(name = "listened_full", nullable = false)
    private boolean fullyListened;

    @Column
    private Integer duration;

    @Column
    private BigDecimal cost;
}
