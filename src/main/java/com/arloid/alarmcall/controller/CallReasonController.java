package com.arloid.alarmcall.controller;

import com.arloid.alarmcall.entity.CallReason;
import com.arloid.alarmcall.service.CallReasonService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("reasons")
public class CallReasonController {

    private final CallReasonService callReasonService;

    @GetMapping
    public ResponseEntity findAll() {
        return ResponseEntity.ok(callReasonService.findAll());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody CallReason callReason) {
        callReasonService.save(callReason);
    }
}
