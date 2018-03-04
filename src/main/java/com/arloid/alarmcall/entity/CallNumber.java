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
public class CallNumber implements Serializable {

    private static final long serialVersionUID = 483218656277389060L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter private Long id;

    @ManyToOne
    @Getter @Setter private Client client;

    @Column(nullable = false)
    @Getter @Setter private String number;

    @Column(nullable = false, insertable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @Getter private Date creation;

    public CallNumber() {}

    public CallNumber(long id) {
        if (id <= 0) {
            throw new IllegalArgumentException("id must not be lower or equal zero");
        }
        this.id = id;
    }

    public CallNumber(long id, Client client) {
        this(id);
        if (client == null) {
            throw new IllegalArgumentException("client must not be null");
        }
        this.client = client;
    }
}
