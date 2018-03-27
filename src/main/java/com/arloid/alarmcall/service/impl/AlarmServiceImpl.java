package com.arloid.alarmcall.service.impl;

import com.amazonaws.services.s3.model.S3Object;
import com.arloid.alarmcall.dto.RegistrationDto;
import com.arloid.alarmcall.entity.Alarm;
import com.arloid.alarmcall.entity.Client;
import com.arloid.alarmcall.repository.AlarmRepository;
import com.arloid.alarmcall.service.AlarmService;
import com.arloid.alarmcall.service.S3Service;
import com.arloid.alarmcall.service.TwiMlFileService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

import static java.util.Objects.nonNull;

@Service
@AllArgsConstructor
public class AlarmServiceImpl implements AlarmService {
    private final S3Service s3Service;
    private final TwiMlFileService twiMlFileService;
    private final AlarmRepository alarmRepository;

    @Override
    public Page<Alarm> findAll(Pageable pageable) {
        return alarmRepository.findAll(pageable);
    }

    @Override
    public Page<Alarm> findByIds(List<Long> ids, Pageable pageable) {
        return alarmRepository.findByIdIn(ids, pageable);
    }

    @Override
    public Alarm save(RegistrationDto.Alarm registrationAlarm, Client client) {
        Alarm alarm = alarmRepository.findByClientId(client.getId());
        if (nonNull(alarm)) {
            throw new RuntimeException("You can't create a new alarm for client " + client.getId() + ". Please, update existing alarm if you need.");
        }
        alarm = new Alarm();
        File twiMl = null;
        switch (registrationAlarm.getType()) {
            case LINK:
                twiMl = twiMlFileService.createSayTwiMl(registrationAlarm.getData());
                alarm.setType(Alarm.AlarmRecordType.TEXT);
                break;
            case TEXT:
                twiMl = twiMlFileService.createPlayTwiMl(registrationAlarm.getData());
                alarm.setType(Alarm.AlarmRecordType.LINK);
                break;
        }
        alarm.setClient(client);
        alarm.setNameRecord(twiMl.getPath());
        alarm.setAddressRecord(s3Service.upload(twiMl));
        return alarmRepository.save(alarm);
    }

    @Override
    public Alarm findByClientId(long clientId) {
        return alarmRepository.findByClientId(clientId);
    }

    @Override
    public S3Object getAlarmFromS3(String key) {
        return s3Service.findObjectByKey(key);
    }
}
