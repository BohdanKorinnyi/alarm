package com.arloid.alarmcall.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import com.arloid.alarmcall.entity.Call;

public interface CallRepository extends Repository<Call, Long> {
    Page<Call> findAll(Pageable pageable);

    Call save(Call call);

    Page<Call> findByCallNumberId(long callNumberId, Pageable pageable);
}
