package com.arloid.alarmcall.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CallNumberDto {
    private long id;
    private long clientId;
    private String number;
    private Date creation;
}
