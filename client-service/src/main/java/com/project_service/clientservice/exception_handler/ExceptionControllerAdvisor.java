package com.project_service.clientservice.exception_handler;

import com.project_service.clientservice.exception.EmailAlreadyExistsException;
import com.project_service.clientservice.exception.ClientAlreadyExistsException;
import com.project_service.clientservice.exception.ClientNotFoundException;
import com.project_service.clientservice.request.ErrorData;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.OffsetDateTime;
import java.util.Arrays;

@RestControllerAdvice
@Profile("!debug")
public class ExceptionControllerAdvisor {

    @ExceptionHandler(ClientAlreadyExistsException.class)
    public ResponseEntity<ErrorData> handleUserAlreadyExistsException(ClientAlreadyExistsException exception) {
        ErrorData errorData = new ErrorData(HttpStatus.BAD_REQUEST, OffsetDateTime.now(),
                exception.getMessage(), Arrays.toString(exception.getStackTrace()));
        return new ResponseEntity<>(errorData, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<ErrorData> handleEmailAlreadyExistsException(EmailAlreadyExistsException exception) {
        ErrorData errorData = new ErrorData(HttpStatus.BAD_REQUEST, OffsetDateTime.now(),
                exception.getMessage(), Arrays.toString(exception.getStackTrace()));
        return new ResponseEntity<>(errorData, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ClientNotFoundException.class)
    public ResponseEntity<ErrorData> handleUserNotFoundException(ClientNotFoundException exception) {
        ErrorData errorData = new ErrorData(HttpStatus.NOT_FOUND, OffsetDateTime.now(),
                exception.getMessage(), Arrays.toString(exception.getStackTrace()));
        return new ResponseEntity<>(errorData, HttpStatus.NOT_FOUND);
    }

}
