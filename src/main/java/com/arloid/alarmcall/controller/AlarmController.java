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

    @PostMapping("upload/{smartHouseClientId}")
    public ResponseEntity uploadVoiceAlarmFile(@RequestParam("file") MultipartFile file, String smartHouseClientId) {
        if (file.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(alarmService.upload(file, smartHouseClientId));
    }

    @PostMapping(value = "{clientId}", produces = MediaType.TEXT_XML_VALUE)
    @ApiOperation(value = "Get Twilio alarm")
    public String getTwiMlAlarm(@PathVariable long clientId) {
        return alarmService.findTwiMlByClient(clientId);
    }
}
