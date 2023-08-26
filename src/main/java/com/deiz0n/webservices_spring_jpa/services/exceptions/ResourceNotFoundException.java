package com.deiz0n.webservices_spring_jpa.services.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(UUID id) {

    }

}
