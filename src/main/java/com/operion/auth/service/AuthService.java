package com.operion.auth.service;

import com.operion.user.entity.User;
import com.operion.user.UserRepository;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final String dummyHash;

    public AuthService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            JwtService jwtService
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.dummyHash = passwordEncoder.encode("timing-equalizer");
    }

    public String login(String username, String email, String password) {

        User user = userRepository.findByUsernameOrEmail(username, email)
                .orElse(null);

        String hashToCheck = (user != null) ? user.getPassword() : dummyHash;
        boolean matches = passwordEncoder.matches(password, hashToCheck);

        if (user == null || !matches) {
            throw new BadCredentialsException("Invalid username or password");
        }

        return jwtService.generateToken(user);
    }
}
