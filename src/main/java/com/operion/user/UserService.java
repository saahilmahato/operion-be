package com.operion.user;

import com.operion.user.dto.CreateUserRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User createUser(CreateUserRequest request) {
        User user = new User();

        user.setFirstName(request.firstName());
        user.setLastName(request.lastName());
        user.setMiddleName(request.middleName());
        user.setEmail(request.email());
        user.setUsername(request.username());
        user.setRole(Role.USER);

        String hashedPassword = passwordEncoder.encode(request.password());
        user.setPassword(hashedPassword);

        return userRepository.save(user);
    }
}
