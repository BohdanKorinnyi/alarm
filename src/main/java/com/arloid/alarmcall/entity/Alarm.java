package com.arloid.alarmcall.entity;

import java.io.Serializable;
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
public class Alarm implements Serializable {

    private static final long serialVersionUID = -4523821848782864384L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter private Long id;

    @ManyToOne
    @Getter @Setter private Client client;

    @Column(nullable = false)
    @Getter @Setter private String nameRecord;

    @Column(nullable = false)
    @Getter @Setter private String addressRecord;

    @Column(nullable = false)
    @Getter @Setter private RecordType type;

    @Column
    @Getter @Setter private boolean active;

    @Column(nullable = false, insertable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @Getter private Date creation;
}
