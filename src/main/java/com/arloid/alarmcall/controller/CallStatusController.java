package com.arloid.alarmcall.controller;

import com.arloid.alarmcall.service.CallStatusService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@AllArgsConstructor
@Api(tags = "Statuses")
public class CallStatusController {
  private final CallStatusService callStatusService;

  @GetMapping("statuses")
  @ApiOperation(value = "Returns possible call statuses")
  public ResponseEntity getStatuses() {
    return ok(callStatusService.findAll());
  }
}
