package com.arloid.alarmcall.service;

import com.arloid.alarmcall.dao.UClientRepository;
import com.arloid.alarmcall.entity.ui.Client;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UClientServiceImpl implements UClientService {
  private final UClientRepository uClientRepository;

  @Override
  public Page<Client> find(Pageable pageable) {
    return uClientRepository.findAll(pageable);
  }
}
