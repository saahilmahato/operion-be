package com.operion.auth;

import com.operion.auth.dto.LoginRequest;
import com.operion.user.User;
import com.operion.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public String login(String username, String email, String password) {

        User user = userRepository.findByUsernameOrEmail(username, email)
                .orElseThrow(() ->
                        new BadCredentialsException("Invalid username or password")
                );

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new BadCredentialsException("Invalid username or password");
        }

        return jwtService.generateToken(user);
    }
}
