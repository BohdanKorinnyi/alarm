package com.arloid.alarmcall.service.impl;

import com.arloid.alarmcall.dto.CallNumberDto;
import com.arloid.alarmcall.dto.ClientDto;
import com.arloid.alarmcall.dto.RegistrationDto;
import com.arloid.alarmcall.entity.Alarm;
import com.arloid.alarmcall.entity.Client;
import com.arloid.alarmcall.service.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
@AllArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {
    private final AlarmService alarmService;
    private final ClientService clientService;
    private final CallNumberService callNumberService;
    private final FileService fileService;
    private final S3Service s3Service;

    @Override
    public long register(RegistrationDto registration) {
        Client client = clientService.save(new ClientDto(registration.getFirstName(), registration.getLastName()));
        callNumberService.save(new CallNumberDto(client.getId(), registration.getPhone()));
        File file = fileService.write(registration.getMessage());
        String upload = s3Service.upload(file);
        Alarm alarm = new Alarm();
        alarm.setClient(client);
        alarm.setNameRecord(file.getPath());
        alarm.setAddressRecord(upload);
        alarm.setType(Alarm.AlarmRecordType.LINK);
        alarmService.save(alarm);
        return client.getId();
    }
}
