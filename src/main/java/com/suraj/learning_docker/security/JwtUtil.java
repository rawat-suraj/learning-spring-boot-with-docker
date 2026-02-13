package com.suraj.learning_docker.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.suraj.learning_docker.dtos.user.UserDto;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
    private final String SECRET_KEY;
    private final long EXPIRATION_TIME = 1000 * 60 * 60; // 1 hour

    public JwtUtil(@Value("${spring.custom.JWT_SECRET_KEY}") String secretKey) {
        this.SECRET_KEY = secretKey;
    }

    public String generateToken(UserDto user) {
        return Jwts.builder()
        .setSubject(user.getEmail())
        .claim("userId", user.getId())
        .setIssuedAt(new Date())
        .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
        .signWith(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()), SignatureAlgorithm.HS256)
        .compact();
    }

    public Long extractUserId(String token) {
        return claims(token)
        .get("userId", Long.class);
    }

    public String extractEmail(String token) {
        return claims(token)
        .getSubject();
    }

    public boolean isValidateToken(String token) {
        try {
            return !claims(token).getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    private Claims claims(String token) {
        return Jwts.parserBuilder()
        .setSigningKey(SECRET_KEY.getBytes())
        .build()
        .parseClaimsJws(token)
        .getBody();
    }
}
