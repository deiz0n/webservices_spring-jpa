package com.deiz0n.webservices_spring_jpa.controllers;

import com.deiz0n.webservices_spring_jpa.models.User;
import com.deiz0n.webservices_spring_jpa.repositories.UserRepository;
import com.deiz0n.webservices_spring_jpa.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/users", produces = {"application/json"})
@Tag(name = "Usuários")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Operation(summary = "Retorna todos os usuários", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso")
    })
    @GetMapping
    public ResponseEntity<List<User>> findAllUsers() {
        List<User> users = userService.getAllResourcers();
        return ResponseEntity.ok(users);
    }

    @Operation(summary = "Retorna determinado usuário", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<User> findUserById(@PathVariable UUID id) {
        var user = userService.getResource(id);
        return ResponseEntity.ok().body(user);
    }

    @Operation(summary = "Cadastra um novo usuário", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuário cadastrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "E-mail já cadastrado"),
            @ApiResponse(responseCode = "400", description = "Telefone já cadastrado")
    })
    @Transactional
    @PostMapping
    public ResponseEntity<?> addUser(@RequestBody @Valid User user) {
        if (userRepository.findFirstByEmail().contains(user.getEmail())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("E-mail already registered");
        }
        if (userRepository.findFirstByPhone().contains(user.getPhone())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Phone already registered");
        }
        var uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).body(userService.createResource(user));
    }

    @Operation(summary = "Deleta determinado usuário", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Usuário excluído com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remUser(@PathVariable UUID id) {
        userService.removeResource(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Atualiza os dados de determinado usuário", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuário atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "E-mail já cadastrado"),
            @ApiResponse(responseCode = "400", description = "Telefone já cadastrado")
    })
    @Transactional
    @PutMapping ("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable UUID id, @RequestBody @Valid User user) {
        if (userRepository.findFirstByEmail().contains(user.getEmail())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("E-mail already registered");
        }
        if (userRepository.findFirstByPhone().contains(user.getPhone())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Phone already registered");
        }
        user = userService.updateResource(id, user);
        return ResponseEntity.ok().body(user);
    }
}
