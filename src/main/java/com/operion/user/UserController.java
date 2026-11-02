package com.operion.user;

import com.operion.user.dto.CreateUserRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/create")
    public ResponseEntity<Map<String, String>> createUser(@Valid @RequestBody CreateUserRequest request) {
        User user = userService.createUser(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(Map.of("message", "User %s create successfully".formatted(user.getUsername())));
    }
}
