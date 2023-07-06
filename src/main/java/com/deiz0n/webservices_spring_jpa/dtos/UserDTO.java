package com.deiz0n.webservices_spring_jpa.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserDTO(
        @NotBlank String name,
        @NotBlank @Email String email,
        @NotBlank @Size(max = 100) String address,
        @NotBlank @Size(min = 13)  String phone,
        @NotNull String password
) {
}
