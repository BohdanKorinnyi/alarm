package com.arloid.alarmcall.controller;

import com.arloid.alarmcall.entity.CallReason;
import com.arloid.alarmcall.service.CallReasonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("reasons")
@Api(description = "Operations with call reasons", tags = "Call reason endpoints")
public class CallReasonController {

    private final CallReasonService callReasonService;

    @GetMapping
    @ApiOperation(value = "Get all reasons")
    public ResponseEntity findAll() {
        return ResponseEntity.ok(callReasonService.findAll());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create a new reason")
    public void save(@RequestBody CallReason callReason) {
        callReasonService.save(callReason);
    }
}
