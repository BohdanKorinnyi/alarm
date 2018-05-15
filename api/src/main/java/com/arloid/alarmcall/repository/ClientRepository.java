package com.arloid.alarmcall.repository;

import com.arloid.alarmcall.entity.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface ClientRepository extends Repository<Client, Long> {
  Optional<Client> findById(Long id);

  Page<Client> findAll(Pageable pageable);

  Client save(Client client);

  Client findByExternalId(String externalId);
}
