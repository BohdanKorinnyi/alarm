package com.arloid.alarmcall.controller;

import com.arloid.alarmcall.entity.Alarm;
import com.arloid.alarmcall.service.AlarmService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("alarms")
@Api(description = "Operations with alarm", tags = "Alarm's endpoints")
public class AlarmController {

    private final AlarmService alarmService;

    @GetMapping
    @ApiOperation(value = "Get all alarms")
    public ResponseEntity<?> findAll(@RequestParam @ApiParam(name = "Page number", defaultValue = "1") int page,
                                  @RequestParam @ApiParam(name = "Elements on page", defaultValue = "20") int size) {
        return ResponseEntity.ok(alarmService.findAll(new PageRequest(--page, size)));
    }

    @GetMapping("{alarmIds}")
    @ApiOperation(value = "Get alarms by ids")
    public ResponseEntity<?> findByIds(@PathVariable List<Long> alarmIds, @RequestParam int page, @RequestParam int size) {
        return ResponseEntity.ok(alarmService.findByIds(alarmIds, new PageRequest(--page, size)));
    }

    @GetMapping("client/{clientId}")
    @ApiOperation(value = "Get alarms by client id")
    public ResponseEntity<?> findByClientId(@PathVariable long clientId, @RequestParam int page, @RequestParam int size) {
        return ResponseEntity.ok(alarmService.findByClientId(clientId, new PageRequest(--page, size)));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Save a new alarm")
    public ResponseEntity<?> save(@RequestBody @ApiParam(value = "A new alarm object", required = true) Alarm alarm) {
        return ResponseEntity.ok(alarmService.save(alarm));
    }

    //TODO: take a look on @DynamicUpdate annotation, it doesn't work as expected for me
//    @PatchMapping
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public ResponseEntity update() {
//        return ResponseEntity.noContent().build();
//    }
}
