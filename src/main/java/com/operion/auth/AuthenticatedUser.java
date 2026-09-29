package com.operion.auth;

public record AuthenticatedUser(
        Long id,
        String username
) {
}
