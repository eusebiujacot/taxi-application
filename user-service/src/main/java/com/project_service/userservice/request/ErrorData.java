package com.project_service.userservice.request;

import org.springframework.http.HttpStatus;

public record ErrorData(HttpStatus httpStatus,
                        java.time.OffsetDateTime timestamp,
                        String message,
                        String details) {
}
