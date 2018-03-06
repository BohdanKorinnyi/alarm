package com.arloid.alarmcall.service.impl;

import com.arloid.alarmcall.entity.CallStatus;
import com.arloid.alarmcall.exception.SaveExistingEntityException;
import com.arloid.alarmcall.repository.CallStatusRepository;
import com.arloid.alarmcall.service.CallStatusService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Objects.nonNull;

@Service
@AllArgsConstructor
public class CallStatusServiceImpl implements CallStatusService {
    private final CallStatusRepository callStatusRepository;

    @Override
    public CallStatus findByName(String name) {
        return callStatusRepository.findByName(name);
    }

    @Override
    public CallStatus save(CallStatus status) {
        if (nonNull(callStatusRepository.findByName(status.getName()))) {
            throw new SaveExistingEntityException("This call status already exists in database");
        }
        return callStatusRepository.save(status);
    }

    @Override
    public List<CallStatus> findAll() {
        return callStatusRepository.findAll();
    }
}
