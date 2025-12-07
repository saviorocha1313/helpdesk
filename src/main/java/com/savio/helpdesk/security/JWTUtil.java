package com.savio.helpdesk.security;

import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTUtil {

    // Você deve definir jwt.secret e jwt.expiration no application.properties
    // Por enquanto, vamos deixar valores padrão se não encontrar
    @Value("${jwt.secret:chave_secreta_padrao}")
    private String secret;

    @Value("${jwt.expiration:6000000}")
    private Long expiration;

    public String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(SignatureAlgorithm.HS512, secret.getBytes())
                .compact();
    }
}