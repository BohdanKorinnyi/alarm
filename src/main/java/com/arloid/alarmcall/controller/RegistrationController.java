package com.arloid.alarmcall.controller;

import com.arloid.alarmcall.dto.RegistrationDto;
import com.arloid.alarmcall.dto.RegistrationSpeechAlarmDto;
import com.arloid.alarmcall.dto.RegistrationVoiceAlarmDto;
import com.arloid.alarmcall.service.RegistrationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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

    @PostMapping("speech")
    @ApiOperation("Register a new client with speech alarm")
    public ResponseEntity registerSpeech(@RequestBody @ApiParam("Message is required for speech alarm") RegistrationDto<RegistrationSpeechAlarmDto> registration) {
        return ResponseEntity.ok(registrationService.register(registration));
    }

    @PostMapping("voice")
    @ApiOperation("Register a new client with voice alarm")
    public ResponseEntity registerVoice(@RequestBody @ApiParam("Record URL is required for voice alarm") RegistrationDto<RegistrationVoiceAlarmDto> registration) {
        return ResponseEntity.ok(registrationService.register(registration));
    }
}
