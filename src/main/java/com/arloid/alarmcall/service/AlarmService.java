package com.arloid.alarmcall.service;

import com.arloid.alarmcall.entity.Alarm;
import com.arloid.alarmcall.entity.Client;
import com.arloid.alarmcall.entity.Language;
import org.springframework.web.multipart.MultipartFile;

public interface AlarmService {
    Alarm save(Client client, Alarm.AlarmRecordType type, String data, Language language);

    String upload(MultipartFile multipartFile, String externalClientId);

    Alarm findByClientId(long clientId);

    String findTwiMlByClient(long clientId);
}
