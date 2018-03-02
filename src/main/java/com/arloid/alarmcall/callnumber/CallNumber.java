package com.arloid.alarmcall.callnumber;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.arloid.alarmcall.client.Client;

@Entity
public class CallNumber implements Serializable {

    private static final long serialVersionUID = -8329525493901880262L;

    @Id
    @SequenceGenerator(name = "call_number_id_seq", sequenceName = "call_number_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "call_number_id_seq")
    private Long id;

    @ManyToOne
    private Client client;

    @Column(nullable = false)
    private String number;

    @Column(nullable = false, insertable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    @Override
    public String toString() {
        return "CallNumber [id=" + id + ", client=" + client + ", number=" + number + ", created=" + created + "]";
    }
}
