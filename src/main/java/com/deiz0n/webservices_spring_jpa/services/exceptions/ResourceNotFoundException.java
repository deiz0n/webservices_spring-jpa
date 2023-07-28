package com.deiz0n.webservices_spring_jpa.services.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(UUID id) {
        super("Error! " + "The resource with the id: " + id + " not found");
    }

}
