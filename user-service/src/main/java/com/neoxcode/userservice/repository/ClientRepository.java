package com.neoxcode.userservice.repository;

import com.neoxcode.userservice.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ClientRepository extends JpaRepository<Client, UUID> {
    Optional<Client> findById(UUID id);
    Optional<Client> findByEmail(String email);
    Optional<Client> findByUserName(String username);
}
