package com.project_service.clientservice.exception;

public class ClientAlreadyExistsException extends RuntimeException {
    public ClientAlreadyExistsException(String message) {
        super(message);
    }
}
