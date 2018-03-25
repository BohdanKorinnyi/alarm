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
    @OneToOne
    private Client client;
    @Column(nullable = false)
    private String number;
    @Column(nullable = false, insertable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date creation;
    @Column
    private Boolean active;
}
