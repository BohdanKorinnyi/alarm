package com.arloid.alarmcall.controller;

import com.arloid.alarmcall.entity.CallNumber;
import com.arloid.alarmcall.service.CallNumberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("numbers")
@Api(tags = "Actions with call numbers")
public class CallNumberController {
    private final CallNumberService callNumberService;

    @GetMapping
    @ApiOperation(value = "Get all phone numbers")
    public ResponseEntity<Page<CallNumber>> findAll(@RequestParam int page, @RequestParam int size) {
        return ResponseEntity.ok(callNumberService.findAll(page, size));
    }

    @GetMapping("client/{clientId}")
    @ApiOperation(value = "Get all client's phone numbers")
    public ResponseEntity<?> findByClientId(@PathVariable long clientId) {
        return ResponseEntity.ok(callNumberService.findByClientId(clientId));
    }
}
