package com.arloid.alarmcall.controller;

import com.arloid.alarmcall.entity.CallNumber;
import com.arloid.alarmcall.service.CallNumberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("numbers")
@Api(description = "Operations with phone numbers")
public class CallNumberController {
    private final CallNumberService callNumberService;

    @GetMapping
    @ApiOperation(value = "Get all phone numbers")
    public ResponseEntity findAll(@RequestParam int page, @RequestParam int size) {
        return ResponseEntity.ok(callNumberService.findAll(page, size));
    }

    @GetMapping("client/{clientId}")
    @ApiOperation(value = "Get all client's phone numbers")
    public ResponseEntity findByClientId(@PathVariable long clientId, @RequestParam int page, @RequestParam int size) {
        return ResponseEntity.ok(callNumberService.findByClientId(clientId, page, size));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create a new phone number")
    public ResponseEntity save(@RequestBody CallNumber callNumber) {
        return ResponseEntity.ok(callNumberService.save(callNumber));
    }
}
