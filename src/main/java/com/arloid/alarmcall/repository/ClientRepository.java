package com.arloid.alarmcall.repository;

import com.arloid.alarmcall.entity.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

public interface ClientRepository extends Repository<Client, Long> {
    Client findOne(Long id);

    Page<Client> findAll(Pageable pageable);

    Client save(Client client);
}
