package com.deiz0n.webservices_spring_jpa.services;

import com.deiz0n.webservices_spring_jpa.models.User;
import com.deiz0n.webservices_spring_jpa.repositories.UserRepository;
import com.deiz0n.webservices_spring_jpa.services.exceptions.DatabaseException;
import com.deiz0n.webservices_spring_jpa.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User getUser(UUID id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public User addUser(User user) {
        return userRepository.save(user);
    }

    public void remUser(UUID id) {
        try {
            userRepository.deleteById(id);
        } catch (EmptyResultDataAccessException error) {
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException error) {
            throw new DatabaseException(error.getMessage());
        }
    }

    public User updateUser(UUID id, User newUserData) {
        try {
            var oldUserData = userRepository.getReferenceById(id);
            updateDataUser(oldUserData, newUserData);
            return userRepository.save(newUserData);
        } catch (EntityNotFoundException error) {
            throw new ResourceNotFoundException(id);
        }
    }

    public void updateDataUser(User oldUserData, User newUserData) {
        oldUserData.setName(newUserData.getName());
        oldUserData.setEmail(newUserData.getEmail());
        oldUserData.setAddress(newUserData.getAddress());
        oldUserData.setPhone(newUserData.getPhone());
        oldUserData.setPassword(newUserData.getPassword());
    }
}
