package com.project_service.userservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project_service.userservice.request.ClientRegistrationRequest;
import com.project_service.userservice.request.ErrorData;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Sql("/database/schema-cleanup.sql")
@Sql("/database/create_tables.sql")
@Sql("/database/add_test_data.sql")
public class ClientControllerExceptionTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testClientWitEmailException() throws Exception {
        // given
        ClientRegistrationRequest clientRegistrationRequest =
                new ClientRegistrationRequest(
                        "uniqueUserk",
                        "eusebiujacot@gmail.com",
                        "bPi2Rf9u0fgKePz%"
                );
        String requestBody = objectMapper.writeValueAsString(clientRegistrationRequest);

        String errorDataJson = mockMvc.
                perform(MockMvcRequestBuilders.post("/api/v1/clients/registrations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isBadRequest())
                .andReturn()
                .getResponse()
                .getContentAsString();
        ErrorData errorData = objectMapper.readValue(errorDataJson, ErrorData.class);
        assertEquals("User with email " + clientRegistrationRequest.email() +
                " already exists", errorData.message());
    }

    @Test
    void testClientWithUserNameException() throws Exception {
        // given
        ClientRegistrationRequest clientRegistrationRequest =
                new ClientRegistrationRequest(
                        "uniqueUserk",
                        "eusebiujacotf@gmail.com",
                        "bPi2Rf9u0fgKePz%"
                );
        String requestBody = objectMapper.writeValueAsString(clientRegistrationRequest);

        String errorDataJson = mockMvc.
                perform(MockMvcRequestBuilders.post("/api/v1/clients/registrations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isBadRequest())
                .andReturn()
                .getResponse()
                .getContentAsString();
        ErrorData errorData = objectMapper.readValue(errorDataJson, ErrorData.class);
        assertEquals("User with this username " + clientRegistrationRequest.username() +
                " already exists", errorData.message());

    }

    @Test
    void testClientWithIdNotFoundException() throws Exception {

    }
}
