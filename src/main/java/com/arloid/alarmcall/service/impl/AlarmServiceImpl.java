package com.arloid.alarmcall.service.impl;

import com.arloid.alarmcall.entity.Alarm;
import com.arloid.alarmcall.entity.Client;
import com.arloid.alarmcall.exception.SaveExistingEntityException;
import com.arloid.alarmcall.repository.AlarmRepository;
import com.arloid.alarmcall.service.AlarmService;
import com.arloid.alarmcall.service.TwilioService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import static java.util.Objects.nonNull;

@Service
@AllArgsConstructor
public class AlarmServiceImpl implements AlarmService {
    private final TwilioService twilioService;
    private final AlarmRepository alarmRepository;

    @Override
    public Alarm save(Client client, Alarm.AlarmRecordType type, String data) {
        Alarm alarm = alarmRepository.findByClientId(client.getId());
        if (nonNull(alarm)) {
            throw new SaveExistingEntityException("You can't create a new alarm for client " + client.getId() + ". Please, update existing alarm if you need.");
        }
        alarm = new Alarm();
        alarm.setType(type);
        alarm.setClient(client);
        alarm.setAddressRecord(data);
        alarm.setActive(true);
        return alarmRepository.save(alarm);
    }

    @Override
    public Alarm findByClientId(long clientId) {
        return alarmRepository.findByClientId(clientId);
    }

    @Override
    public String findTwiMlByClient(long clientId) {
        Alarm alarm = alarmRepository.findByClientId(clientId);
        if (alarm.getType() == Alarm.AlarmRecordType.SPEECH) {
            return twilioService.buildSayResponse(alarm.getAddressRecord());
        }
        return twilioService.buildPlayResponse(alarm.getAddressRecord());
    }
}
