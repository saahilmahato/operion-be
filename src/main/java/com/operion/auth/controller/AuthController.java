package com.operion.auth.controller;

import com.operion.auth.service.AuthService;
import com.operion.auth.service.CookieService;
import com.operion.auth.dto.LoginRequest;
import com.operion.auth.entity.AuthenticatedUser;
import com.operion.user.entity.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final CookieService cookieService;

    @PostMapping("/login")
    public ResponseEntity<User> login(
            @Valid @RequestBody LoginRequest request
    ) {
        String token = authService.login(request.identifier(), request.identifier(), request.password());

        return ResponseEntity.noContent()
                .header(HttpHeaders.SET_COOKIE, cookieService.create(token).toString())
                .build();
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout() {
        return ResponseEntity.noContent()
                .header(HttpHeaders.SET_COOKIE, cookieService.clear().toString())
                .build();
    }

    @GetMapping("/me")
    public AuthenticatedUser me(@AuthenticationPrincipal AuthenticatedUser user) {
        return user;
    }
}
