package com.project_service.userservice.controller;


import com.project_service.userservice.entity.ClientApp;
import com.project_service.userservice.request.ClientRegistrationRequest;
import com.project_service.userservice.service.ClientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/clients")
public class ClientController {
    private final ClientService clientService;

    @PostMapping("/registrations")
    @ResponseStatus(HttpStatus.CREATED)
    public String reregistrationClient(@RequestBody @Valid ClientRegistrationRequest clientRegistrationRequest) {
        log.info("new client registration request: {}", clientRegistrationRequest);
        return clientService.registrationClients(clientRegistrationRequest);
    }

    @GetMapping("/{id}")
    public ClientApp findClientById(@PathVariable("id") String id) {
        log.info("find client by id: {}", id);
        return clientService.findClientById(id);
    }
}
