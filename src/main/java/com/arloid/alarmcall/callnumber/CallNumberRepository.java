package com.arloid.alarmcall.callnumber;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

public interface CallNumberRepository extends Repository<CallNumber, Long> {

    Page<CallNumber> findAll(Pageable pageable);

    CallNumber save(CallNumber callNumber);

    Page<CallNumber> findByClientId(long id, Pageable pageable);
}
