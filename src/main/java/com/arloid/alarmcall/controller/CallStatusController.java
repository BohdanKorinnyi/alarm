package com.arloid.alarmcall.controller;

import com.arloid.alarmcall.entity.CallStatus;
import com.arloid.alarmcall.service.CallStatusService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("statuses")
@Api(description = "Operations with call statuses", tags = "Call status endpoints")
public class CallStatusController {

    private final CallStatusService callStatusService;

    @GetMapping
    @ApiOperation(value = "Get all statuses")
    public ResponseEntity findAll() {
        return ResponseEntity.ok(callStatusService.findAll());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create a new status")
    public ResponseEntity<CallStatus> save(@RequestBody CallStatus callStatus) {
        return ResponseEntity.ok(callStatusService.save(callStatus));
    }
}
