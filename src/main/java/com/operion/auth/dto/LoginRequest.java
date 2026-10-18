package com.operion.auth.dto;

import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
        @NotBlank(message = "identifier is missing")
        String identifier,

        @NotBlank(message = "password is missing")
        String password
) {
}
