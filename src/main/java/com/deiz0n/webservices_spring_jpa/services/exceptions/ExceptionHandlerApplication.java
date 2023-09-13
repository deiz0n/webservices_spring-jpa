package com.deiz0n.webservices_spring_jpa.services.exceptions;

import com.deiz0n.webservices_spring_jpa.repositories.UserRepository;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerApplication extends ResponseEntityExceptionHandler {

    @Autowired
    private UserRepository repository;

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> notFoundHandler() {
        var error = new DefaultError(HttpStatus.NOT_FOUND.value(), "Resource not found");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<?> databaseException() {
        var error = new DefaultError(HttpStatus.CONFLICT.value(), "Resource in use");
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

}
