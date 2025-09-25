package com.example.trial_test.config;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;
import java.util.Map;

public class JwtUtil {

    // Secret key (make it at least 256-bit for HS256)
    private static final String SECRET = "your-256-bit-secret-your-256-bit-secret"; // 32+ chars
    private static final Key KEY = Keys.hmacShaKeyFor(SECRET.getBytes());

    // Token validity: 1 hour
    private static final long EXPIRATION_MS = 3600000;

    // Generate JWT token with claims
    public static String generateToken(String username, Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)        // optional custom claims
                .setSubject(username)      // who the token is for
                .setIssuedAt(new Date())   // token issue time
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_MS)) // expiry
                .signWith(KEY)             // use Key object
                .compact();
    }

    // Validate token and get claims
    public static Claims  validateToken(String token) throws JwtException {
        return Jwts.parserBuilder()
                .setSigningKey(KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // Get username from token
    public static String getUsername(String token) {
        return validateToken(token).getSubject();
    }

    // Check if token expired
    public static boolean isTokenExpired(String token) {
        return validateToken(token).getExpiration().before(new Date());
    }
}