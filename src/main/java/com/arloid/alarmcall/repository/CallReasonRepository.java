package com.arloid.alarmcall.repository;

import com.arloid.alarmcall.entity.CallReason;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface CallReasonRepository extends JpaRepository<CallReason, Long> {
    CallReason findByName(String name);
}
