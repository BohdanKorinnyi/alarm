package com.arloid.alarmcall.controller;

import com.arloid.alarmcall.entity.Client;
import com.arloid.alarmcall.service.ClientService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@AllArgsConstructor
@RequestMapping("clients")
@Api(tags = "Clients")
public class ClientController {
  private final ClientService clientService;

  @GetMapping
  @ApiOperation(value = "Returns all clients")
  public ResponseEntity findAll(Pageable pageable) {
    return ok(clientService.findAll(pageable));
  }

  @PutMapping
  @ResponseStatus(HttpStatus.NO_CONTENT)
  @ApiOperation(value = "Updates the existing client")
  public void update(@RequestBody Client client) {
    clientService.update(client);
  }
}
