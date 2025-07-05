package com.hospital.hospitalcrud.seguridad;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JWTService {

    @Value("${jwt.secret}")
    private String secreto;

    @Value("${jwt.expiration}")
    private long expiracion; // en milisegundos

    public String generarToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiracion))
                .signWith(SignatureAlgorithm.HS512, secreto)
                .compact();
    }

    public String obtenerUsernameDelToken(String token) {
        return Jwts.parser()
                .setSigningKey(secreto)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean validarToken(String token) {
        try {
            Jwts.parser().setSigningKey(secreto).parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }
}
