package com.deiz0n.webservices_spring_jpa.services.exceptions;

import java.util.UUID;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(UUID id) {
        super("The resource with the id: " + id + "not found");
    }

}
