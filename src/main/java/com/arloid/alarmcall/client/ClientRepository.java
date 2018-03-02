package com.arloid.alarmcall.client;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

public interface ClientRepository extends Repository<Client, Long> {

    Client findOne(Long id);

    Page<Client> findAll(Pageable pageable);

    Page<Client> findByFirstNameContaining(String firstName, Pageable pageable);

    Client save(Client client);
}
