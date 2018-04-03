package com.arloid.alarmcall.repository;

import com.arloid.alarmcall.entity.CallNumber;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CallNumberRepository extends JpaRepository<CallNumber, Long> {
    CallNumber findById(long callNumberId);

    CallNumber findByClientId(long clientId);
}
