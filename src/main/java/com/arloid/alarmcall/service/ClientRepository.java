package com.arloid.alarmcall.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import com.arloid.alarmcall.entity.Client;

public interface ClientRepository extends Repository<Client, Long> {

    Client findOne(Long id);

    Page<Client> findAll(Pageable pageable);

    Page<Client> findByFirstNameContaining(String name, Pageable pageable);

    Client save(Client client);
}
