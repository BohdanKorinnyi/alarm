package com.arloid.alarmcall.dao;

import com.arloid.alarmcall.entity.ui.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

public interface UClientRepository extends Repository<Client, Long> {
  Page<Client> findAll(Pageable pageable);
}
