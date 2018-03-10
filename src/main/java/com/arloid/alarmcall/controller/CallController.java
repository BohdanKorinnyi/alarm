package com.arloid.alarmcall.controller;

import com.arloid.alarmcall.dto.CallDto;
import com.arloid.alarmcall.service.CallService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("calls")
@Api(description = "Operations with calls", tags = "Call endpoints")
public class CallController {

    private final CallService callService;

    @GetMapping
    @ApiOperation(value = "Get all calls")
    public ResponseEntity<?> findAll(@RequestParam int size, @RequestParam int page) {
        return ResponseEntity.ok(callService.findAll(size, page));
    }

    @GetMapping("number/{numberId}")
    @ApiOperation(value = "Get all calls by number ID")
    public ResponseEntity<?> findByCallNumberId(@PathVariable long numberId, @RequestParam int size, @RequestParam int page) {
        return ResponseEntity.ok(callService.findByCallNumberId(numberId, size, page));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create a new call")
    public ResponseEntity<?> save(@RequestBody CallDto call) {
        return ResponseEntity.ok(callService.save(call));
    }
}
