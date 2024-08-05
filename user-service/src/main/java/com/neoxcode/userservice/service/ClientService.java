package com.neoxcode.userservice.service;

import com.neoxcode.userservice.entity.Client;
import com.neoxcode.userservice.repository.ClientRepository;
import com.neoxcode.userservice.request.ClientRegistrationRequest;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Data
@Service
public class ClientService {
    private final ClientRepository clientRepository;

    public String registrationClients(ClientRegistrationRequest clientRegistrationRequest) {
        Optional<Client> optionalClientEmail = clientRepository.findByEmail(clientRegistrationRequest.email());
        if (optionalClientEmail.isPresent()) {
            throw new IllegalArgumentException("User with this email already exists");
        }

        Optional<Client> optionalClientUsername = clientRepository.findByUserName(clientRegistrationRequest.username());
        if (optionalClientUsername.isPresent()) {
            throw new IllegalArgumentException("User with this username already exists");
        }

        Client client = Client.builder()
                .email(clientRegistrationRequest.email())
                .userName(clientRegistrationRequest.username())
                .password(clientRegistrationRequest.password())
                .build();
        clientRepository.save(client);
        return String.valueOf(client.getId());
    }

    public Client findClientById(String id) {
        return clientRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new IllegalArgumentException(String.format("User with id %s not found", id))) ;
    }
}
