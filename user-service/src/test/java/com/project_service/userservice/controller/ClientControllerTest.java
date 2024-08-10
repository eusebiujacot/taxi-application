package com.project_service.userservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project_service.userservice.request.ClientRegistrationRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Sql("/database/schema-cleanup.sql")
@Sql("/database/create_tables.sql")
@Sql("/database/add_test_data.sql")
class ClientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private static Validator validator;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation
                .buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void reregistrationClient() throws Exception {
        // given
        ClientRegistrationRequest clientRegistrationRequest =
                new ClientRegistrationRequest(
                        "ClientTaxi",
                        "clientTaksi@gmail.com",
                        "bPi2Rf9u0fgKePz%"
                );

        String requestBody = objectMapper.writeValueAsString(clientRegistrationRequest);

        //when
        String registrationClientAppRequest = mockMvc.
                perform(MockMvcRequestBuilders
                        .post("/api/v1/clients/registrations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString();
        assertNotNull(registrationClientAppRequest);
    }


    @Test
    void testValidRegistrationRequest() {
        ClientRegistrationRequest clientRegistrationRequest =
                new ClientRegistrationRequest(
                        "ClientTaxi",
                        "clientTaxi@gmail.com",
                        "bPi2Rf9u0fgKePz%"
                );
        Set<ConstraintViolation<ClientRegistrationRequest>> constraintViolations =
                validator.validate(clientRegistrationRequest);
        if (!constraintViolations.isEmpty()) {
            for (ConstraintViolation<ClientRegistrationRequest> constraintViolation :
                    constraintViolations) {
                System.out.println(constraintViolation.getMessage());
            }
        }
        assertTrue(constraintViolations.isEmpty());
    }

    @Test
    void testEmptyUsername() {
        ClientRegistrationRequest clientRegistrationRequest =
                new ClientRegistrationRequest(
                        "",
                        "clientTaxi@gmail.com",
                        "bPi2Rf9u0fgKePz%"
                );
        Set<ConstraintViolation<ClientRegistrationRequest>> constraintViolations =
                validator.validate(clientRegistrationRequest);
        if (!constraintViolations.isEmpty()) {
            for (ConstraintViolation<ClientRegistrationRequest> constraintViolation :
                    constraintViolations) {
                System.out.println(constraintViolation.getPropertyPath() +
                        ": " + constraintViolation.getMessage());
            }
        }
        assertEquals(2, constraintViolations.size(),
                "Expected two validation errors");

        Set<String> message = constraintViolations.stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.toSet());
        assertTrue(message.contains("Can not be empty"),
                "Expected 'Can not be empty', message");
        assertTrue(message.contains("First name must contain only letters"),
                "Expected 'First name must contain only letters', message");
    }

    @Test
    void testInvalidPassword() {
        ClientRegistrationRequest clientRegistrationRequest = new ClientRegistrationRequest(
                "ClientTaxi",
                "clientTaxi@gmail.com",
                ""

        );
        Set<ConstraintViolation<ClientRegistrationRequest>> constraintViolations =
                validator.validate(clientRegistrationRequest);
        if (!constraintViolations.isEmpty()) {
            for (ConstraintViolation<ClientRegistrationRequest> constraintViolation : constraintViolations) {
                System.out.println(constraintViolation.getPropertyPath() +
                        ": " + constraintViolation.getMessage());
            }
        }
        assertEquals(3, constraintViolations.size(), "Expected three validation errors");
        Set<String> message = constraintViolations.stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.toSet());
        assertTrue(message.contains("Can not be empty"),
                "Expected 'Can not be empty' message");
        assertTrue(message.contains("Password mast by between 5 and 20 characters"),
                "Expected 'Password mast by between 5 and 20 characters'");
        assertTrue(message.contains("Password is required to contain only English alphabet " +
                        "characters at least one uppercase and one lowercase, also one digit and one special character"),
                "Expected 'Password is required to contain only English alphabet characters " +
                        "at least one uppercase and one lowercase, also one digit and one special character' message");
    }

    @Test
    void testEmptyEmail() {
        ClientRegistrationRequest clientRegistrationRequest = new ClientRegistrationRequest(
                "ClientTaxi",
                "",
                "bPi2Rf9u0fgKePz%"
        );
        Set<ConstraintViolation<ClientRegistrationRequest>> constraintViolations =
                validator.validate(clientRegistrationRequest);
        if (!constraintViolations.isEmpty()) {
            for (ConstraintViolation<ClientRegistrationRequest> constraintViolation : constraintViolations) {
                System.out.println(constraintViolation.getPropertyPath() +
                        ": " + constraintViolation.getMessage());
            }
        }
        assertEquals(1, constraintViolations.size());
        assertEquals("Email cannot be empty",
                constraintViolations.iterator().next().getMessage());
    }

    @Test
    void testInvalidEmail() {
        ClientRegistrationRequest clientRegistrationRequest = new ClientRegistrationRequest(
                "ClientTaxi",
                "12345",
                "bPi2Rf9u0fgKePz%"
        );
        Set<ConstraintViolation<ClientRegistrationRequest>> constraintViolations =
                validator.validate(clientRegistrationRequest);
        if (!constraintViolations.isEmpty()) {
            for (ConstraintViolation<ClientRegistrationRequest> constraintViolation : constraintViolations) {
                System.out.println(constraintViolation.getPropertyPath() +
                        ": " + constraintViolation.getMessage());
            }
        }
        assertEquals(1, constraintViolations.size());
        assertEquals("Email should be valid",
                constraintViolations.iterator().next().getMessage());
    }

}