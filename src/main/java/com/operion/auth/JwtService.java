package com.operion.auth;

import com.operion.user.User;
import org.springframework.stereotype.Service;

@Service
public class JwtService {

    public String generateToken(User user) {
        return "generated-jwt";
    }
}
