package com.arloid.alarmcall.controller;

import com.arloid.alarmcall.entity.Client;
import com.arloid.alarmcall.service.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("clients")
public class ClientController {

    private final ClientService clientService;

    @GetMapping
    public ResponseEntity<?> findAll(@RequestParam int page, @RequestParam int size) {
        return ResponseEntity.ok(clientService.findAll(page, size));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> save(@RequestBody Client client) {
        return ResponseEntity.ok(clientService.save(client));
    }
}
