package com.arloid.alarmcall.service.impl;

import com.arloid.alarmcall.entity.CallReason;
import com.arloid.alarmcall.exception.ExistingCallReasonCreationException;
import com.arloid.alarmcall.repository.CallReasonRepository;
import com.arloid.alarmcall.service.CallReasonService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Objects.nonNull;

@Service
@AllArgsConstructor
public class CallReasonServiceImpl implements CallReasonService {
    private final CallReasonRepository callReasonRepository;

    @Override
    public CallReason findByName(String name) {
        return callReasonRepository.findByName(name);
    }

    @Override
    public void save(CallReason callReason) {
        if (nonNull(callReasonRepository.findByName(callReason.getName()))) {
            throw new ExistingCallReasonCreationException("This call reason already exists in database");
        }
        callReasonRepository.save(callReason);
    }

    @Override
    public List<CallReason> findAll() {
        return callReasonRepository.findAll();
    }
}
