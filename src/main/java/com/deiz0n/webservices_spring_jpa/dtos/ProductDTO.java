package com.deiz0n.webservices_spring_jpa.dtos;

import jakarta.validation.constraints.NotBlank;

public record ProductDTO(
        @NotBlank String name,
        @NotBlank String description,
        @NotBlank Double price
) {
}
