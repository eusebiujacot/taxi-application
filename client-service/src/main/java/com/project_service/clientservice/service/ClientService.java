package com.project_service.clientservice.service;

import com.project_service.clientservice.entity.ClientApp;
import com.project_service.clientservice.exception.EmailAlreadyExistsException;
import com.project_service.clientservice.exception.ClientAlreadyExistsException;
import com.project_service.clientservice.exception.ClientNotFoundException;
import com.project_service.clientservice.repository.ClientRepository;
import com.project_service.clientservice.request.ClientRegistrationRequest;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Data
@Service
public class ClientService {
    private final ClientRepository clientRepository;
    private final PasswordEncoder passwordEncoder;

    public String registrationClients(ClientRegistrationRequest clientRegistrationRequest) {
        Optional<ClientApp> optionalClientAppEmail =
                clientRepository.findByEmail(clientRegistrationRequest.email());
        if (optionalClientAppEmail.isPresent()) {
            throw new EmailAlreadyExistsException("User with email "
                    + clientRegistrationRequest.email() + " already exists");
        }

        Optional<ClientApp> optionalClientUsername =
                clientRepository.findByClientName(clientRegistrationRequest.username());
        if (optionalClientUsername.isPresent()) {
            throw new ClientAlreadyExistsException("User with this username "
                    + clientRegistrationRequest.username() + " already exists");
        }

        ClientApp clientApp = ClientApp.builder()
                .email(clientRegistrationRequest.email())
                .clientName(clientRegistrationRequest.username())
                .password(passwordEncoder.encode(clientRegistrationRequest.password()))
                .build();
        clientRepository.save(clientApp);

        return String.valueOf(clientApp.getId());
    }

    public ClientApp findClientById(String id) {
        return clientRepository.findById(UUID.fromString(id))
                .orElseThrow(() ->
                        new ClientNotFoundException
                                (String.format("User with id %s not found", id)));
    }
}
