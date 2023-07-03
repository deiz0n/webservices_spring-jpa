package com.deiz0n.webservices_spring_jpa.repositories;

import com.deiz0n.webservices_spring_jpa.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
}
