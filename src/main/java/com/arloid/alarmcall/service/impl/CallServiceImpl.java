package com.arloid.alarmcall.service.impl;

import com.arloid.alarmcall.entity.Call;
import com.arloid.alarmcall.repository.CallRepository;
import com.arloid.alarmcall.service.CallService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CallServiceImpl implements CallService {

    private final CallRepository callRepository;

    @Override
    public Page<Call> findAll(int size, int page) {
        return callRepository.findAll(createPageable(page, size));
    }

    @Override
    public Call save(Call call) {
        return callRepository.save(call);
    }

    @Override
    public Page<Call> findByCallNumberId(long callNumberId, int page, int size) {
        return callRepository.findByCallNumberId(callNumberId, createPageable(page, size));
    }

    private Pageable createPageable(int page, int size) {
        return new PageRequest(--page, size);
    }
}
