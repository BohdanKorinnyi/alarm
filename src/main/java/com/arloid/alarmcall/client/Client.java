package com.arloid.alarmcall.client;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.arloid.alarmcall.common.RecordType;

@Entity
public class Client implements Serializable {

    private static final long serialVersionUID = -1429773722913190050L;

    @Id
    @SequenceGenerator(name = "client_id_seq", sequenceName = "client_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "client_id_seq")
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String nameRecord;

    @Column(nullable = false)
    private String addressRecord;

    @Column(nullable = false)
    private RecordType recordType;

    @Column(nullable = false, insertable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date registered;

    public Client() {}

    public Client(long id) {
        if (id <= 0) {
            throw new IllegalArgumentException("id must not be lower or equal zero");
        }
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNameRecord() {
        return nameRecord;
    }

    public void setNameRecord(String nameRecord) {
        this.nameRecord = nameRecord;
    }

    public String getAddressRecord() {
        return addressRecord;
    }

    public void setAddressRecord(String addressRecord) {
        this.addressRecord = addressRecord;
    }

    public RecordType getRecordType() {
        return recordType;
    }

    public void setRecordType(RecordType recordType) {
        this.recordType = recordType;
    }

    public Date getRegistered() {
        return registered;
    }

    public void setRegistered(Date registered) {
        this.registered = registered;
    }

    @Override
    public String toString() {
        return "Client [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", nameRecord=" + nameRecord
                + ", addressRecord=" + addressRecord + ", recordType=" + recordType + ", registered=" + registered + "]";
    }
}
