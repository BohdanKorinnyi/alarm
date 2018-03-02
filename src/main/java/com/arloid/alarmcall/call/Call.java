package com.arloid.alarmcall.call;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.arloid.alarmcall.callnumber.CallNumber;
import com.arloid.alarmcall.client.Client;

@Entity
public class Call implements Serializable {

    private static final long serialVersionUID = 4467746466190983921L;

    @Id
    @SequenceGenerator(name = "call_id_seq", sequenceName = "call_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "call_id_seq")
    private Long id;

    @ManyToOne
    private Client client;

    @ManyToOne
    private CallNumber callNumber;

    @Column(nullable = false, insertable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date updated;

    @Column(nullable = false)
    private String reason;

    @Column(nullable = false)
    private String provider;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private boolean fullyListened;

    @Column(nullable = false)
    private int duration;

    @Column
    private BigDecimal cost;

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

    public CallNumber getCallNumber() {
        return callNumber;
    }

    public void setCallNumber(CallNumber callNumber) {
        this.callNumber = callNumber;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isFullyListened() {
        return fullyListened;
    }

    public void setFullyListened(boolean fullyListened) {
        this.fullyListened = fullyListened;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Call [id=" + id + ", client=" + client + ", callNumber=" + callNumber + ", created=" + created
                + ", updated=" + updated + ", reason=" + reason + ", provider=" + provider + ", status=" + status
                + ", fullyListened=" + fullyListened + ", duration=" + duration + ", cost=" + cost + "]";
    }

}
