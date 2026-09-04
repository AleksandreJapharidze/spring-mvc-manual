package com.manualspring.util;

import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
    private static final long EXPIRATION_TIME = 1000 * 60 * 20;
    private static final String SECRET = "super-secret-string-that-is-long-enough-for-256-bit";

    public String generateToken(String username) {
        return Jwts.builder()
            .subject(username)
            .issuedAt(new Date())
            .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
            .signWith(Keys.hmacShaKeyFor(SECRET.getBytes()))
            .compact();
    }
}
