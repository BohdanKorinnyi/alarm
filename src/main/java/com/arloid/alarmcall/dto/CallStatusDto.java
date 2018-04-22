package com.arloid.alarmcall.dto;

import com.arloid.alarmcall.entity.CallStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CallStatusDto {
  private long id;
  private String name;

  public CallStatus convert() {
    CallStatus callStatus = new CallStatus();
    callStatus.setId(id);
    callStatus.setName(name);
    return callStatus;
  }
}
