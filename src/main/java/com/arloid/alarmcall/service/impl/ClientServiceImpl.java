package com.arloid.alarmcall.service.impl;

import com.arloid.alarmcall.entity.Client;
import com.arloid.alarmcall.repository.ClientRepository;
import com.arloid.alarmcall.service.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;

    @Override
    public Client findOne(Long clientId) {
        return clientRepository.findOne(clientId);
    }

    @Override
    public Page<Client> findAll(int page, int size) {
        return clientRepository.findAll(createPageable(page, size));
    }

    @Override
    public Client save(Client client) {
        return clientRepository.save(client);
    }

    private Pageable createPageable(int page, int size) {
        return new PageRequest(--page, size);
    }
}
