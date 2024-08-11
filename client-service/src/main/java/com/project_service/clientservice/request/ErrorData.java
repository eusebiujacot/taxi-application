package com.project_service.clientservice.request;

import org.springframework.http.HttpStatus;

public record ErrorData(HttpStatus httpStatus,
                        java.time.OffsetDateTime timestamp,
                        String message,
                        String details) {
}
