package com.deiz0n.webservices_spring_jpa.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductDTO(
        @NotBlank String name,
        @NotBlank String description,
        @NotNull Double price
) {
}
