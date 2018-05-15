package com.arloid.alarmcall.service;

import com.arloid.alarmcall.dto.RegistrationClientDto;
import com.arloid.alarmcall.entity.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ClientService {
  Optional<Client> findOne(Long id);

  Page<Client> findAll(Pageable pageable);

  Client save(RegistrationClientDto client);

  void update(Client client);
}
