package com.arloid.alarmcall.controller;

import com.arloid.alarmcall.service.UCallService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@AllArgsConstructor
@RequestMapping("api/calls")
public class UCallController {
  private final UCallService uCallService;

  @GetMapping
  public ResponseEntity getCalls(Pageable pageable) {
    return ResponseEntity.ok(uCallService.find(pageable));
  }

  @GetMapping("numbers")
  public ResponseEntity getCallsByNumber(@RequestParam String number, Pageable pageable) {
    return ResponseEntity.ok(uCallService.findByNumber(number, pageable));
  }
}
