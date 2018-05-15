package com.arloid.alarmcall.service;

import com.arloid.alarmcall.entity.ui.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UClientService {
  Page<Client> find(Pageable pageable);
}
