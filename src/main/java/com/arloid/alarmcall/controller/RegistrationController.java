package com.arloid.alarmcall.controller;

import com.arloid.alarmcall.dto.RegistrationDto;
import com.arloid.alarmcall.service.RegistrationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("registrations")
@Api(tags = "New client registration")
public class RegistrationController {
    private final RegistrationService registrationService;

    @PostMapping
    @ApiOperation("Register a new client")
    public ResponseEntity register(@RequestBody RegistrationDto registration) {
        return ResponseEntity.ok(registrationService.register(registration));
    }
}
