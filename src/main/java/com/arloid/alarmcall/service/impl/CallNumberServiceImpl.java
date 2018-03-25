package com.arloid.alarmcall.service.impl;

import com.arloid.alarmcall.dto.CallNumberDto;
import com.arloid.alarmcall.entity.CallNumber;
import com.arloid.alarmcall.entity.Client;
import com.arloid.alarmcall.repository.CallNumberRepository;
import com.arloid.alarmcall.service.CallNumberService;
import com.arloid.alarmcall.service.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import static java.util.Objects.isNull;

@Service
@AllArgsConstructor
public class CallNumberServiceImpl implements CallNumberService {
    private final CallNumberRepository callNumberRepository;
    private final ClientService clientService;

    @Override
    public CallNumber findById(long callNumberId) {
        return callNumberRepository.findOne(callNumberId);
    }

    @Override
    public Page<CallNumber> findAll(int page, int size) {
        return callNumberRepository.findAll(createPageable(page, size));
    }

    @Override
    public CallNumber save(CallNumberDto callNumberDto) {
        Client client = clientService.findOne(callNumberDto.getClientId());
        if (isNull(client)) {
            throw new IllegalArgumentException("The following client id '" + callNumberDto.getClientId() + "' doesn't exist");
        }
        CallNumber callNumber = new CallNumber();
        callNumber.setClient(client);
        callNumber.setNumber(callNumberDto.getNumber());
        callNumber.setActive(true);
        return callNumberRepository.save(callNumber);
    }

    @Override
    public CallNumber findByClientId(long clientId) {
        return callNumberRepository.findByClientId(clientId);
    }

    private Pageable createPageable(int page, int size) {
        return new PageRequest(--page, size);
    }
}
