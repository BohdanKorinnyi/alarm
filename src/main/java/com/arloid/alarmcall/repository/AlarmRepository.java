package com.arloid.alarmcall.repository;

import com.arloid.alarmcall.entity.Alarm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface AlarmRepository extends Repository<Alarm, Long> {
    Page<Alarm> findAll(Pageable pageable);

    Page<Alarm> findByIdIn(List<Long> ids, Pageable pageable);

    Alarm save(Alarm alarm);

    Page<Alarm> findByClientId(long clientId, Pageable pageable);
}
