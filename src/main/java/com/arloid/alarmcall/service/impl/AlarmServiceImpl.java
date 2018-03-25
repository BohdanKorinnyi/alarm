package com.arloid.alarmcall.service.impl;

import com.amazonaws.services.s3.model.S3Object;
import com.arloid.alarmcall.entity.Alarm;
import com.arloid.alarmcall.repository.AlarmRepository;
import com.arloid.alarmcall.service.AlarmService;
import com.arloid.alarmcall.service.S3Service;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AlarmServiceImpl implements AlarmService {
    private final S3Service s3Service;
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
    public Alarm save(Alarm alarm) {
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
