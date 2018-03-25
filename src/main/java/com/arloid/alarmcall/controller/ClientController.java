package com.arloid.alarmcall.controller;

import com.amazonaws.services.s3.AmazonS3;
import com.arloid.alarmcall.dto.ClientDto;
import com.arloid.alarmcall.entity.Client;
import com.arloid.alarmcall.service.ClientService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("clients")
@Api(tags = "Actions with clients")
public class ClientController {

    private final ClientService clientService;

    @GetMapping
    @ApiOperation(value = "Get all clients")
    public ResponseEntity<?> findAll(@RequestParam @ApiParam(defaultValue = "1") int page,
                                     @RequestParam @ApiParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(clientService.findAll(page, size));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create a new client")
    public ResponseEntity<?> save(@RequestBody ClientDto client) {
        return ResponseEntity.ok(clientService.save(client));
    }

    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Update the existing client")
    public void update(@RequestBody Client client) {
        clientService.update(client);
    }
}
