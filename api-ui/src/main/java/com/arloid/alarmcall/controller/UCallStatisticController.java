package com.arloid.alarmcall.controller;

import com.arloid.alarmcall.service.UCallStatisticService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("api/stats")
public class UCallStatisticController {
  private final UCallStatisticService uCallStatisticService;

  @GetMapping("general")
  public ResponseEntity getCallStatistic() {
    return ResponseEntity.ok(uCallStatisticService.getCallStatistic());
  }

  @GetMapping("countries")
  public ResponseEntity getCountryStatistic() {
    return ResponseEntity.ok(uCallStatisticService.getCountryStatistic());
  }
}
