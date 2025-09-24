package com.infosys.BudgetTracker.security;


import com.infosys.BudgetTracker.entity.Users;

import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

    private final String SECRET = "SecretKeyForJWT";
    private final long EXPIRATION = 86400000; // 1 day

    // Generate token with email as subject & role as claim
    public String generateToken(Users user) {
        return Jwts.builder()
                .setSubject(user.getEmail())
                .claim("role", user.getRole().name())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .compact();
    }


    // Extract username/email
    public String extractUsername(String token) {
        return getClaims(token).getSubject();
    }

    // Extract role
    public String extractRole(String token) {
        return getClaims(token).get("role", String.class);
    }

    // Validate token
    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    // Helper
    private Claims getClaims(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody();
    }
}
