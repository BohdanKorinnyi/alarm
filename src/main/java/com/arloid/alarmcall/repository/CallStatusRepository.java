package com.arloid.alarmcall.repository;

import com.arloid.alarmcall.entity.CallStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CallStatusRepository extends JpaRepository<CallStatus, Long> {
    CallStatus findByName(String name);
}
