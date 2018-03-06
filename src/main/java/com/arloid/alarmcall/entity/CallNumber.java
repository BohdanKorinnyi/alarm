package com.arloid.alarmcall.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
public class CallNumber implements Serializable {

    private static final long serialVersionUID = 483218656277389060L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Client client;

    @Column(nullable = false)
    private String number;

    @Column(nullable = false, insertable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date creation;

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
