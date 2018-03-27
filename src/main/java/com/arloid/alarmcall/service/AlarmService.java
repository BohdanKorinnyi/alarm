package com.arloid.alarmcall.service;

import com.arloid.alarmcall.entity.Alarm;
import com.arloid.alarmcall.entity.Client;

public interface AlarmService {
    Alarm save(Client client, Alarm.AlarmRecordType type, String data);

    Alarm findByClientId(long clientId);

    String findTwiMlByClient(long clientId);
}
