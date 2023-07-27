package com.deiz0n.webservices_spring_jpa.services;

import java.util.List;
import java.util.UUID;

public interface ServiceReader<T> {

    List<T> getAllResources();
    T getResource(UUID id);
}
