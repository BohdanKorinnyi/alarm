package com.arloid.alarmcall.service;

import com.amazonaws.services.s3.model.S3Object;
import com.arloid.alarmcall.dto.RegistrationDto;
import com.arloid.alarmcall.entity.Alarm;
import com.arloid.alarmcall.entity.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AlarmService {
    Alarm save(RegistrationDto.Alarm alarm, Client client);

    Alarm findByClientId(long clientId);

    S3Object getAlarmFromS3(String key);
}
