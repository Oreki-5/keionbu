package com.oreki5.keionbu.config;

import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.oreki5.keionbu.dbEntities.Users;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String SECRET;

    public String generateToken(Users user) throws NoSuchAlgorithmException {

        Map<String,String> claims = new HashMap<>();
        claims.put("role", user.getRole());
        return Jwts.builder()
                    .claims().add(claims)
                    .and()
                    .subject(user.getUsername())
                    .issuedAt(new Date(System.currentTimeMillis()))
                    .expiration(new Date(System.currentTimeMillis()+60*60*60*5))
                    .signWith(generateKey())
                    .compact();

    }

    public SecretKey generateKey() {
        byte[] decodedSecret = Base64.getDecoder().decode(SECRET);
        return Keys.hmacShaKeyFor(decodedSecret);
    }

    public Claims getClaims(String token) {
        return Jwts.parser()
                .verifyWith(generateKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public String getUsernameFromToken(String token) {
        Claims claims = getClaims(token);
        return claims.getSubject();
    }

    public boolean verifyToken(String token) {
        Claims claims = getClaims(token);
        return claims.getExpiration().after(Date.from(Instant.now()));
    }
}
