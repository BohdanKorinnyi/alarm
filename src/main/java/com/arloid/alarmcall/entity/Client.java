package com.arloid.alarmcall.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Client implements Serializable {

    private static final long serialVersionUID = 1205794308713377888L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private boolean proof;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date registered;

    public Client() {}

    public Client(long id) {
        if (id <= 0) {
            throw new IllegalArgumentException("id must not be lower or equal zero");
        }
        this.id = id;
    }
}
