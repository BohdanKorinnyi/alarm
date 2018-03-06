package com.arloid.alarmcall.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import com.arloid.alarmcall.entity.CallNumber;

public interface CallNumberRepository extends Repository<CallNumber, Long> {
    CallNumber findOne(long callNumberId);

    Page<CallNumber> findAll(Pageable pageable);

    CallNumber save(CallNumber callNumber);

    Page<CallNumber> findByClientId(long id, Pageable pageable);
}
