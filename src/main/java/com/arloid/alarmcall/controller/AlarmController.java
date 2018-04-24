package com.arloid.alarmcall.controller;

import com.arloid.alarmcall.service.AlarmService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.status;

@RestController
@AllArgsConstructor
@RequestMapping("alarms")
@Api(tags = "Alarms")
public class AlarmController {
  private final AlarmService alarmService;

  @GetMapping("clients/{clientId}")
  @ApiOperation(value = "Returns alarms by the client id")
  public ResponseEntity findByClientId(@PathVariable long clientId) {
    return ok(alarmService.findByClientId(clientId));
  }

  @PostMapping("uploading/{smartHouseClientId}")
  @ApiOperation(value = "Uploads the alarm audio file")
  public ResponseEntity uploadVoiceAlarmFile(
      @RequestParam("file") MultipartFile file, String smartHouseClientId) {
    if (file.isEmpty()) {
      return status(HttpStatus.BAD_REQUEST).build();
    }
    return ok(alarmService.upload(file, smartHouseClientId));
  }

  @PostMapping(value = "{clientId}", produces = MediaType.TEXT_XML_VALUE)
  @ApiOperation(value = "Returns TwiMl by the client id")
  public ResponseEntity getTwiMlAlarm(@PathVariable long clientId) {
    return ok(alarmService.findTwiMlByClient(clientId));
  }
}
