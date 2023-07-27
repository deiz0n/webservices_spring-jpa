package com.deiz0n.webservices_spring_jpa.services;

import java.util.List;
import java.util.UUID;

public interface ServiceCRUD<T> {

    List<T> getAllResourcers();
    T getResource(UUID id);
    T createResource(T t);
    void removeResource(UUID id);
    T updateResource(UUID id, T newResourceData);
    void updateDataResource(T oldResourceData, T newResourceData);

}
