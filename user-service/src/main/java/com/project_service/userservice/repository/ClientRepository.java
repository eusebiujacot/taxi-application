package com.project_service.userservice.repository;

import com.project_service.userservice.entity.ClientApp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ClientRepository extends JpaRepository<ClientApp, UUID> {
    Optional<ClientApp> findById(UUID id);
    Optional<ClientApp> findByEmail(String email);
    Optional<ClientApp> findByUserName(String username);
}
