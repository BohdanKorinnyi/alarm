package com.arloid.alarmcall.controller;

import com.arloid.alarmcall.service.UClientService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("api/clients")
public class UClientController {
  private final UClientService uClientService;

  @GetMapping
  public ResponseEntity getClient(Pageable pageable) {
    return ResponseEntity.ok(uClientService.find(pageable));
  }
}
