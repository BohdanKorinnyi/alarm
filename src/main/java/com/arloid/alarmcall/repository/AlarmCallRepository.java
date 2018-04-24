package com.arloid.alarmcall.repository;

import com.arloid.alarmcall.entity.AlarmCall;
import com.arloid.alarmcall.entity.CallStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface AlarmCallRepository extends Repository<AlarmCall, Long> {
  List<AlarmCall> findByCallStatus(CallStatus callStatus);

  AlarmCall save(AlarmCall alarmCall);

  Page<AlarmCall> findAll(Pageable pageable);

  Page<AlarmCall> findByCallNumberId(long callNumberId, Pageable pageable);

  AlarmCall findByProviderId(String twilioId);

  Integer countByParentId(long parentId);
}
