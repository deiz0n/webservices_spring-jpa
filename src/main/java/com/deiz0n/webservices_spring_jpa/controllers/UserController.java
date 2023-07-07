package com.deiz0n.webservices_spring_jpa.controllers;

import com.deiz0n.webservices_spring_jpa.dtos.UserDTO;
import com.deiz0n.webservices_spring_jpa.models.Product;
import com.deiz0n.webservices_spring_jpa.models.User;
import com.deiz0n.webservices_spring_jpa.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.UUID;

@Transactional
@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Transactional(readOnly = true)
    @GetMapping
    public ResponseEntity<List<User>> findAllUsers() {
        var users = userService.getUsers();
        return ResponseEntity.ok(users);
    }

    @Transactional(readOnly = true)
    @GetMapping(value = "/{id}")
    public ResponseEntity<User> findUserById(@PathVariable UUID id) {
        var user = userService.getUser(id);
        return ResponseEntity.ok().body(user);
    }

    @PostMapping
    public ResponseEntity<User> addUser(@RequestBody @Valid UserDTO userDTO) {
        var user = new User();
        BeanUtils.copyProperties(userDTO, user);
        var uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).body(userService.addUser(user));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> remUser(@PathVariable UUID id) {
        userService.remUser(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<User> updateUser(@PathVariable UUID id, @RequestBody @Valid User user, UserDTO userDTO) {
        user = userService.updateUser(id, user);
        BeanUtils.copyProperties(user, userDTO);
        return ResponseEntity.ok().body(user);
    }

}
