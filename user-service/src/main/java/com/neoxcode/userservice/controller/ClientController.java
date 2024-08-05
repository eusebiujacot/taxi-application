package com.neoxcode.userservice.controller;


import com.neoxcode.userservice.entity.Client;
import com.neoxcode.userservice.request.ClientRegistrationRequest;
import com.neoxcode.userservice.service.ClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/clients")
public class ClientController {
    private final ClientService clientService;

    @PostMapping("/registrations")
    @ResponseStatus(HttpStatus.CREATED)
    public String reregistrationClient(@RequestBody ClientRegistrationRequest clientRegistrationRequest) {
        log.info("new client registration request: {}", clientRegistrationRequest);
        return clientService.registrationClients(clientRegistrationRequest);
    }

    @GetMapping("/{id}")
    public Client findClientById(@PathVariable("id") String id) {
        log.info("find client by id: {}", id);
        return clientService.findClientById(id);
    }


}
