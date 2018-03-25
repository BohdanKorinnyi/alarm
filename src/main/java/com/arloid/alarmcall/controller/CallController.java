package com.arloid.alarmcall.controller;

import com.arloid.alarmcall.entity.Call;
import com.arloid.alarmcall.service.CallService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("calls")
@Api(tags = "Actions with calls")
public class CallController {

    private final CallService callService;

    @GetMapping
    @ApiOperation(value = "Get all calls")
    public ResponseEntity<Page<Call>> findAll(@RequestParam int size, @RequestParam int page) {
        return ResponseEntity.ok(callService.findAll(size, page));
    }

    @GetMapping("number/{numberId}")
    @ApiOperation(value = "Get all calls by number ID")
    public ResponseEntity<Page<Call>> findByCallNumberId(@PathVariable long numberId, @RequestParam int size, @RequestParam int page) {
        return ResponseEntity.ok(callService.findByCallNumberId(numberId, size, page));
    }

    @PostMapping("client/{clientId}")
    @ApiOperation(value = "Make a call by client id")
    public ResponseEntity<?> makeToClient(@PathVariable long clientId) {
        return ResponseEntity.ok(callService.makeByClientId(clientId));
    }

    @PostMapping("number/{phoneNumberId}")
    @ApiOperation(value = "Make a call by phone number id")
    public ResponseEntity<?> makeToNumber(@PathVariable long phoneNumberId) {
        return ResponseEntity.ok(callService.makeByPhoneNumberId(phoneNumberId));
    }
}
