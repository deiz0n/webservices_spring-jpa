package com.deiz0n.webservices_spring_jpa.repositories;

import com.deiz0n.webservices_spring_jpa.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, UUID> {
}
