package com.arloid.alarmcall.controller;

import com.arloid.alarmcall.entity.AlarmCall;
import com.arloid.alarmcall.service.CallService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@AllArgsConstructor
@Api(tags = "Calls")
@RequestMapping("calls")
public class CallController {
  private final CallService callService;

  @GetMapping
  @ApiOperation(value = "Returns all calls")
  public ResponseEntity<Page<AlarmCall>> findAll(Pageable pageable) {
    return ok(callService.findAll(pageable));
  }

  @GetMapping("numbers/{numberId}")
  @ApiOperation(value = "Returns all calls by the number id")
  public ResponseEntity<Page<AlarmCall>> findByCallNumberId(
      @PathVariable long numberId, Pageable pageable) {
    return ok(callService.findByCallNumberId(pageable, numberId));
  }

  @PostMapping("clients/{clientId}")
  @ApiOperation(value = "Makes a call by the client id")
  public ResponseEntity makeToClient(@PathVariable long clientId) {
    callService.makeByClientId(clientId);
    return ok().build();
  }
}
