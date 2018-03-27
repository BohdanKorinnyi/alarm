package com.arloid.alarmcall.controller;

import com.arloid.alarmcall.service.AlarmService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("alarms")
@Api(tags = "Actions with alarms")
public class AlarmController {
    private final AlarmService alarmService;

    @GetMapping("client/{clientId}")
    @ApiOperation(value = "Get alarms by client id")
    public ResponseEntity findByClientId(@PathVariable long clientId) {
        return ResponseEntity.ok(alarmService.findByClientId(clientId));
    }

    @PostMapping(value = "{clientId}", produces = MediaType.TEXT_XML_VALUE)
    @ApiOperation(value = "Get Twilio alarm")
    public String getTwiMlAlarm(@PathVariable long clientId) {
        return alarmService.findTwiMlByClient(clientId);
    }
}
