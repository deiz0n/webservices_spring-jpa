package com.deiz0n.webservices_spring_jpa.services.exceptions;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerApplication extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity notFoundHandler() {
        var error = new DefaultError(HttpStatus.NOT_FOUND.value(), "Resource not found");
        return new ResponseEntity(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity dataIntegrityException() {
        var error = new DefaultError(HttpStatus.CONFLICT.value(), "Resource in use");
        return new ResponseEntity(error, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity emailOrPhoneAlreadyException() {
        var error = new DefaultError(HttpStatus.CONFLICT.value(), "email or telephone number already registered");
        return new ResponseEntity(error, HttpStatus.CONFLICT);
    }

}
