package com.project_service.userservice.service;

import com.project_service.userservice.entity.ClientApp;
import com.project_service.userservice.exception.EmailAlreadyExistsException;
import com.project_service.userservice.exception.UserAlreadyExistsException;
import com.project_service.userservice.exception.UserNotFoundException;
import com.project_service.userservice.repository.ClientRepository;
import com.project_service.userservice.request.ClientRegistrationRequest;
import lombok.Data;
import org.springframework.dao.DataIntegrityViolationException;
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
                clientRepository.findByUserName(clientRegistrationRequest.username());
        if (optionalClientUsername.isPresent()) {
            throw new UserAlreadyExistsException("User with this username "
                    + clientRegistrationRequest.username() + " already exists");
        }

        ClientApp clientApp = ClientApp.builder()
                .email(clientRegistrationRequest.email())
                .userName(clientRegistrationRequest.username())
                .password(passwordEncoder.encode(clientRegistrationRequest.password()))
                .build();
        clientRepository.save(clientApp);

        return String.valueOf(clientApp.getId());
    }

    public ClientApp findClientById(String id) {
        return clientRepository.findById(UUID.fromString(id))
                .orElseThrow(() ->
                        new UserNotFoundException
                                (String.format("User with id %s not found", id)));
    }
}
