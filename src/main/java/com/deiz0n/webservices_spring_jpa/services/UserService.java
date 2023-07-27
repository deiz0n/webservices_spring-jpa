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
public class UserService implements ServiceCRUD<User> {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllResourcers() {
        return userRepository.findAll();
    }

    @Override
    public User getResource(UUID id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    @Override
    public User createResource(User user) {
        user.setPassword(user.getPassword());
        return userRepository.save(user);
    }

    @Override
    public void removeResource(UUID id) {
        try {
            userRepository.deleteById(id);
        } catch (EmptyResultDataAccessException error) {
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException error) {
            throw new DatabaseException(error.getMessage());
        }
    }

    @Override
    public User updateResource(UUID id, User newResourceData) {
        try {
            var oldUserData = userRepository.getReferenceById(id);
            updateDataResource(oldUserData, newResourceData);
            return userRepository.save(newResourceData);
        } catch (EntityNotFoundException error) {
            throw new ResourceNotFoundException(id);
        }
    }

    @Override
    public void updateDataResource(User oldResourceData, User newResourceData) {
        oldResourceData.setName(newResourceData.getName());
        oldResourceData.setEmail(newResourceData.getEmail());
        oldResourceData.setAddress(newResourceData.getAddress());
        oldResourceData.setPhone(newResourceData.getPhone());
        oldResourceData.setPassword(newResourceData.getPassword());
    }
}
