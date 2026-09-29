package com.operion.auth;

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
    private final String dummyHash = passwordEncoder.encode("timing-equalizer");

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
