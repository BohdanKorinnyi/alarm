package com.arloid.alarmcall.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import com.arloid.alarmcall.entity.Call;

public interface CallRepository extends Repository<Call, Long> {
    Call save(Call call);

    Page<Call> findAll(Pageable pageable);

    Page<Call> findByCallNumberId(long callNumberId, Pageable pageable);

    Call findByProviderId(String twilioId);
}
