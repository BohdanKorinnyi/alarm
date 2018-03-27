package com.arloid.alarmcall.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistrationDto<T> {
    private RegistrationClientDto client;
    private RegistrationPhoneDto phone;
    private T alarm;
}
