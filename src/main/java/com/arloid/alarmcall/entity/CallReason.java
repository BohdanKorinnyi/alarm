package com.arloid.alarmcall.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "call_reason")
public class CallReason {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "value")
    private String name;
}
