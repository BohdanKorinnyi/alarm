package com.arloid.alarmcall.service;

import com.arloid.alarmcall.entity.Alarm;
import com.arloid.alarmcall.entity.Client;
import com.arloid.alarmcall.entity.Language;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

public interface AlarmService {
  Page<Alarm> find(Pageable pageable);

  Alarm save(Client client, Alarm.AlarmRecordType type, String data, Language language);

  String upload(MultipartFile multipartFile, String externalClientId);

  Alarm findByClientId(long clientId);

  String getTwiMlByClient(long clientId);
}
