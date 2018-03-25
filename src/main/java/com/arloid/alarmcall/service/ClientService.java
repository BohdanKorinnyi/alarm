package com.arloid.alarmcall.service;

import com.arloid.alarmcall.dto.ClientDto;
import com.arloid.alarmcall.entity.Client;
import org.springframework.data.domain.Page;

public interface ClientService {
    Client findOne(Long id);

    Page<Client> findAll(int page, int size);

    Client save(ClientDto clientDto);

    void update(Client client);
}
