package com.project_service.userservice.exception_handler;

import com.project_service.userservice.exception.EmailAlreadyExistsException;
import com.project_service.userservice.exception.UserAlreadyExistsException;
import com.project_service.userservice.exception.UserNotFoundException;
import com.project_service.userservice.request.ErrorData;
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
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorData> handleIllegalArgumentException(IllegalArgumentException ex) {
        ErrorData errorData = new ErrorData(HttpStatus.INTERNAL_SERVER_ERROR, OffsetDateTime.now(),
                ex.getMessage(), Arrays.toString(ex.getStackTrace()));
        return new ResponseEntity<>(errorData, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ErrorData> handleUserAlreadyExistsException(UserAlreadyExistsException exception) {
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

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorData> handleUserNotFoundException(UserNotFoundException exception) {
        ErrorData errorData = new ErrorData(HttpStatus.NOT_FOUND, OffsetDateTime.now(),
                exception.getMessage(), Arrays.toString(exception.getStackTrace()));
        return new ResponseEntity<>(errorData, HttpStatus.NOT_FOUND);
    }

}
