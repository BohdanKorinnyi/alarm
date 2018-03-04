package com.arloid.alarmcall.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import com.arloid.alarmcall.entity.Alarm;

public interface AlarmRepository extends Repository<Alarm, Long> {

    Page<Alarm> findAll(Pageable pageable);

    Alarm save(Alarm alarm);

    Page<Alarm> findByClientId(long id, Pageable pageable);
}
