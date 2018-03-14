package com.arloid.alarmcall.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import org.hibernate.envers.Audited;

import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
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
    @Audited
    private Boolean proof;

    @Column(insertable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date registered;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date updated;

    public Client(long id) {
        if (id <= 0) {
            throw new IllegalArgumentException("id must not be lower or equal zero");
        }
        this.id = id;
    }
}
