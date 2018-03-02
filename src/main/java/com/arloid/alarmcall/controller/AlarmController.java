package com.arloid.alarmcall.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("alarms")
public class AlarmController {

    @GetMapping
    public ResponseEntity findAll() {
        return ResponseEntity.noContent().build();
    }

    @GetMapping("{alarmIds}")
    public ResponseEntity findById(@PathVariable List<Long> alarmIds) {
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity save() {
        return ResponseEntity.noContent().build();
    }

    @PatchMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity update() {
        return ResponseEntity.noContent().build();
    }

}
