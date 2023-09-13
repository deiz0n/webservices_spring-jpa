package com.deiz0n.webservices_spring_jpa.repositories;

import com.deiz0n.webservices_spring_jpa.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;


public interface UserRepository extends JpaRepository<User, UUID> {

    @Query("SELECT u.email FROM User u")
    List<String> findFirstByEmail();

    @Query("SELECT u.phone FROM User u")
    List<String> findFirstByPhone();

}
