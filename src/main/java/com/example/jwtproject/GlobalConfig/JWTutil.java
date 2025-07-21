package com.example.jwtproject.GlobalConfig;

import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JWTutil {
   
    
    private static final Key key= Keys.secretKeyFor(SignatureAlgorithm.HS256);

    private static final long EXPIRATION_TIME=60*60*1000;

    public String generateToken(String userName)
    {
        return Jwts.builder()
        .setSubject(userName)
        .setIssuer("Showspot")
        .setIssuedAt(new Date())
        .setExpiration(new Date(System.currentTimeMillis() +EXPIRATION_TIME))
        .signWith(key)
        .compact();
    }


    private Claims getClaims(String token)
    {
        return Jwts.parser()
                   .setSigningKey(key)
                   .build()
                   .parseClaimsJws(token)
                   .getBody();
    }
}
