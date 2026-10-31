package com.operion.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record CreateUserRequest(
        @NotBlank(message = "First name is required")
        String firstName,

        @NotBlank(message = "Last name is required")
        String lastName,

        String middleName,

        @NotBlank(message = "Address is required")
        String address,

        @NotBlank(message = "Email is required")
        String email,

        @NotBlank(message = "Phone number is required")
        String phoneNumber,

        @NotBlank(message = "Username is required")
        String username,

        @NotBlank(message = "Password is required")
        @Size(min = 8, message = "Password must be at least 8 characters")
        @Pattern(
                regexp = "^(?=.*[A-Z])(?=.*[^a-zA-Z0-9]).+$",
                message = "Password must contain at least one uppercase letter and one special character"
        )
        String password
) {
}
