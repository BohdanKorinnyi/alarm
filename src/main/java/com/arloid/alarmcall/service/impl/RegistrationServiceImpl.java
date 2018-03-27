package com.arloid.alarmcall.service.impl;

import com.arloid.alarmcall.dto.RegistrationDto;
import com.arloid.alarmcall.dto.RegistrationSpeechAlarmDto;
import com.arloid.alarmcall.dto.RegistrationVoiceAlarmDto;
import com.arloid.alarmcall.entity.Alarm;
import com.arloid.alarmcall.entity.Client;
import com.arloid.alarmcall.service.AlarmService;
import com.arloid.alarmcall.service.CallNumberService;
import com.arloid.alarmcall.service.ClientService;
import com.arloid.alarmcall.service.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {
    private final AlarmService alarmService;
    private final ClientService clientService;
    private final CallNumberService callNumberService;

    @Override
    public Client register(RegistrationDto registration) {
        Client client = clientService.save(registration.getClient());
        callNumberService.save(registration.getPhone(), client.getId());
        if (registration.getAlarm() instanceof RegistrationSpeechAlarmDto) {
            RegistrationSpeechAlarmDto alarm = (RegistrationSpeechAlarmDto) registration.getAlarm();
            alarmService.save(client, Alarm.AlarmRecordType.SPEECH, alarm.getMessage());
        } else if (registration.getAlarm() instanceof RegistrationVoiceAlarmDto) {
            RegistrationVoiceAlarmDto alarm = (RegistrationVoiceAlarmDto) registration.getAlarm();
            alarmService.save(client, Alarm.AlarmRecordType.LINK, alarm.getUrl());
        }
        return client;
    }
}
