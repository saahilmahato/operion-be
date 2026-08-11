package com.operion.user.dto;

public record CreateUserRequest(
        String firstName,
        String lastName,
        String middleName,
        String email,
        String username,
        String password
) {}
