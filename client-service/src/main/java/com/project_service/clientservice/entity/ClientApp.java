package com.project_service.clientservice.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class ClientApp {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String clientName;

    private String firstName;

    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    private String phoneNumber;
    private String address;
    private String city;
    private String country;
    private Boolean isActive;
    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;
    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientApp clientApp = (ClientApp) o;
        return Objects.equals(id, clientApp.id) && Objects.equals(firstName,
                clientApp.firstName) && Objects.equals(lastName, clientApp.lastName) && Objects.equals(email,
                clientApp.email) && Objects.equals(phoneNumber, clientApp.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, email, phoneNumber);
    }
}
