package com.arloid.alarmcall.service.impl;

import com.arloid.alarmcall.entity.CallNumber;
import com.arloid.alarmcall.repository.CallNumberRepository;
import com.arloid.alarmcall.service.CallNumberService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CallNumberServiceImpl implements CallNumberService {
    private final CallNumberRepository callNumberRepository;

    @Override
    public Page<CallNumber> findAll(int page, int size) {
        return callNumberRepository.findAll(createPageable(page, size));
    }

    @Override
    public CallNumber save(CallNumber callNumber) {
        return callNumberRepository.save(callNumber);
    }

    @Override
    public Page<CallNumber> findByClientId(long clientId, int page, int size) {
        return callNumberRepository.findByClientId(clientId, createPageable(page, size));
    }

    private Pageable createPageable(int page, int size) {
        return new PageRequest(--page, size);
    }
}
