package com.arloid.alarmcall.repository;

import com.arloid.alarmcall.entity.CallStatus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CallStatusRepository extends CrudRepository<CallStatus, Long> {
}
