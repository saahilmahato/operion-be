package com.operion.auth.filter;

import com.nimbusds.jwt.JWTClaimsSet;
import com.operion.auth.entity.AuthenticatedUser;
import com.operion.auth.property.JwtProperties;
import com.operion.auth.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.WebUtils;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final JwtProperties props;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain chain) throws ServletException, IOException {

        Cookie cookie = WebUtils.getCookie(request, props.cookieName());

        if (cookie != null && !cookie.getValue().isBlank()) {
            try {
                JWTClaimsSet claims = jwtService.parseAndValidate(cookie.getValue());

                var principal = new AuthenticatedUser(
                        Long.valueOf(claims.getSubject()),
                        claims.getStringClaim("username")
                );

                var authorities = List.of(new SimpleGrantedAuthority("ROLE_" + claims.getStringClaim("role")));

                SecurityContextHolder.getContext().setAuthentication(
                        new UsernamePasswordAuthenticationToken(principal, null, authorities)
                );
            } catch (AuthenticationException | java.text.ParseException e) {
                SecurityContextHolder.clearContext();
            }
        }

        chain.doFilter(request, response);
    }
}
