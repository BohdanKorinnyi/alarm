package com.arloid.alarmcall.controller;

import com.amazonaws.services.s3.model.S3Object;
import com.arloid.alarmcall.entity.Alarm;
import com.arloid.alarmcall.service.AlarmService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("alarms")
@Api(tags = "Actions with alarms")
public class AlarmController {

    private final AlarmService alarmService;

    @GetMapping
    @ApiOperation(value = "Get all alarms")
    public ResponseEntity<?> findAll(@RequestParam @ApiParam(name = "Page number", defaultValue = "1") int page,
                                     @RequestParam @ApiParam(name = "Elements on page", defaultValue = "20") int size) {
        return ResponseEntity.ok(alarmService.findAll(new PageRequest(--page, size)));
    }

    @GetMapping("{alarmIds}")
    @ApiOperation(value = "Get alarms by ids")
    public ResponseEntity<?> findByIds(@PathVariable List<Long> alarmIds, @RequestParam int page, @RequestParam int size) {
        return ResponseEntity.ok(alarmService.findByIds(alarmIds, new PageRequest(--page, size)));
    }

    @GetMapping("client/{clientId}")
    @ApiOperation(value = "Get alarms by client id")
    public ResponseEntity<?> findByClientId(@PathVariable long clientId) {
        return ResponseEntity.ok(alarmService.findByClientId(clientId));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Save a new alarm")
    public ResponseEntity<?> save(@RequestBody @ApiParam(value = "A new alarm object", required = true) Alarm alarm) {
        return ResponseEntity.ok(alarmService.save(alarm));
    }

    @PostMapping("{key}")
    @ApiOperation(value = "Get alarm for Twilio from AWS S3")
    public ResponseEntity<?> getAlarmFromS3(@PathVariable String key) {
        S3Object alarm = alarmService.getAlarmFromS3(key);
        return ResponseEntity.ok()
                .contentLength(alarm.getObjectMetadata().getContentLength())
                .contentType(MediaType.TEXT_XML)
                .body(new InputStreamResource(alarm.getObjectContent()));
    }
}
