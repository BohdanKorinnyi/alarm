package com.arloid.alarmcall.service.impl;

import com.arloid.alarmcall.dto.CallDto;
import com.arloid.alarmcall.entity.Call;
import com.arloid.alarmcall.entity.CallNumber;
import com.arloid.alarmcall.repository.CallRepository;
import com.arloid.alarmcall.service.CallNumberService;
import com.arloid.alarmcall.service.CallService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import static java.util.Objects.isNull;

@Service
@AllArgsConstructor
public class CallServiceImpl implements CallService {
    private final CallRepository callRepository;
    private final CallNumberService callNumberService;

    @Override
    public Page<Call> findAll(int size, int page) {
        return callRepository.findAll(createPageable(page, size));
    }

    @Override
    public Call save(CallDto callDto) {
        CallNumber callNumber = callNumberService.findById(callDto.getCallNumberId());
        if (isNull(callNumber)) {
            throw new IllegalArgumentException("The following call number id '" + callDto.getCallNumberId() + "' doesn't exist");
        }
        Call call = new Call();
        call.setCallNumber(callNumber);
        call.setCreated(callDto.getCreated());
        call.setUpdated(callDto.getUpdated());
        call.setProvider(callDto.getProvider());
        call.setFullyListened(callDto.getFullyListened());
        call.setDuration(callDto.getDuration());
        call.setCost(callDto.getCost());
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
