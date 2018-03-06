package com.arloid.alarmcall.service;

import com.arloid.alarmcall.entity.Alarm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AlarmService {
    Page<Alarm> findAll(Pageable pageable);

    Page<Alarm> findByIds(List<Long> ids, Pageable pageable);

    Alarm save(Alarm alarm);

    Page<Alarm> findByClientId(long id, Pageable pageable);
}
