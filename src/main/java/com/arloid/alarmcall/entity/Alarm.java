package com.arloid.alarmcall.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Setter
@Getter
//@DynamicUpdate
public class Alarm implements Serializable {

    private static final long serialVersionUID = -4523821848782864384L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Client client;

    @Column(nullable = false)
    private String nameRecord;

    @Column(nullable = false)
    private String addressRecord;

    @Column(nullable = false)
    private AlarmRecordType type;

    @Column
    private Boolean active;

    @Column(nullable = false, insertable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date creation;

    public enum AlarmRecordType {
        LINK, TEXT;
    }
}