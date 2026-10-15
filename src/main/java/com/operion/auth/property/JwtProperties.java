package com.operion.auth.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.time.Duration;

@ConfigurationProperties(prefix = "jwt")
public record JwtProperties(
        RSAPublicKey publicKey,
        RSAPrivateKey privateKey,
        String keyId,
        String issuer,
        Duration accessTokenTtl,
        String cookieName,
        boolean cookieSecure
) {
}
