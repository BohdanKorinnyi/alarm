package com.arloid.alarmcall.service;

import com.arloid.alarmcall.dto.RegistrationDto;
import com.arloid.alarmcall.entity.Client;

public interface RegistrationService {
    Client register(RegistrationDto registrationDto);
}
