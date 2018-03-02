package com.arloid.alarmcall.alarm;

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
import com.arloid.alarmcall.common.RecordType;

@Entity
public class Alarm implements Serializable {

    private static final long serialVersionUID = 3410352054862960656L;

    @Id
    @SequenceGenerator(name = "alarm_id_seq", sequenceName = "alarm_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "alarm_id_seq")
    private Long id;

    @ManyToOne
    private Client client;

    @Column(nullable = false)
    private String record;

    @Column(nullable = false)
    private RecordType recordType;

    @Column(nullable = false, insertable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

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

    public String getRecord() {
        return record;
    }

    public void setRecord(String record) {
        this.record = record;
    }

    public RecordType getRecordType() {
        return recordType;
    }

    public void setRecordType(RecordType recordType) {
        this.recordType = recordType;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    @Override
    public String toString() {
        return "Alarm [id=" + id + ", client=" + client + ", record=" + record + ", recordType=" + recordType
                + ", created=" + created + "]";
    }
}
