package com.arloid.alarmcall.controller;

import com.arloid.alarmcall.service.CallNumberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@AllArgsConstructor
@RequestMapping("numbers")
@Api(tags = "Numbers")
public class CallNumberController {
  private final CallNumberService callNumberService;

  @GetMapping
  @ApiOperation(value = "Returns all numbers")
  public ResponseEntity findAll(Pageable pageable) {
    return ok(callNumberService.findAll(pageable));
  }

  @GetMapping("clients/{clientId}")
  @ApiOperation(value = "Returns all numbers by client id")
  public ResponseEntity findByClientId(@PathVariable long clientId) {
    return ok(callNumberService.findByClientId(clientId));
  }
}
