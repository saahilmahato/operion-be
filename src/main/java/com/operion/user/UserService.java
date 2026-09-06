package com.operion.user;

import com.operion.user.dto.CreateUserRequest;
import com.operion.user.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public User createUser(CreateUserRequest request) {
        User user = userMapper.toEntity(request);

        user.setRole(Role.USER);
        user.setPassword(passwordEncoder.encode(request.password()));

        return userRepository.save(user);
    }
}
