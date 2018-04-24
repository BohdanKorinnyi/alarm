package com.arloid.alarmcall.repository;

import com.arloid.alarmcall.entity.AlarmCall;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

public interface CallRepository extends Repository<AlarmCall, Long> {
  AlarmCall save(AlarmCall alarmCall);

  Page<AlarmCall> findAll(Pageable pageable);

  Page<AlarmCall> findByCallNumberId(long callNumberId, Pageable pageable);

  AlarmCall findByProviderId(String twilioId);

  Integer countByParentId(long parentId);
}
