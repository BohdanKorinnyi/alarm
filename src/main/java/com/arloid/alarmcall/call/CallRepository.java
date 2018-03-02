package com.arloid.alarmcall.call;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

public interface CallRepository extends Repository<Call, Long> {

    Page<Call> findAll(Pageable pageable);

    Call save(Call call);

    Page<Call> findByClientId(long id, Pageable pageable);

    Page<Call> findByClientIdAndCallNumberId(long clientId, long callNumberId, Pageable pageable);
}
