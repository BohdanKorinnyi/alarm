package com.arloid.alarmcall.service.impl;

import com.arloid.alarmcall.dto.RegistrationClientDto;
import com.arloid.alarmcall.entity.Client;
import com.arloid.alarmcall.exception.SaveExistingEntityException;
import com.arloid.alarmcall.repository.ClientRepository;
import com.arloid.alarmcall.service.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static java.util.Objects.nonNull;

@Service
@AllArgsConstructor
public class ClientServiceImpl implements ClientService {
  private final ClientRepository clientRepository;

  @Override
  public Optional<Client> findOne(Long clientId) {
    return clientRepository.findById(clientId);
  }

  @Override
  public Page<Client> findAll(Pageable pageable) {
    return clientRepository.findAll(pageable);
  }

  @Override
  public Client save(RegistrationClientDto registrationClient) {
    Client client = clientRepository.findByExternalId(registrationClient.getId());
    if (nonNull(client)) {
      throw new SaveExistingEntityException(
          "Client with id " + registrationClient.getId() + " has already exist");
    }
    client = new Client();
    client.setFirstName(registrationClient.getFirstName());
    client.setLastName(registrationClient.getLastName());
    client.setExternalId(registrationClient.getId());
    return clientRepository.save(client);
  }

  @Override
  public void update(Client client) {
    clientRepository.findById(client.getId()).ifPresent(client1 -> clientRepository.save(client));
  }
}
