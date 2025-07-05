package com.hospital.hospitalcrud.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.function.Function;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;

    // Generar token
    public String generarToken(UserDetails userDetails) {
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    // Extraer username del token
    public String extraerUsername(String token) {
        return extraerReclamacion(token, Claims::getSubject);
    }

    // Verificar si el token es válido
    public boolean validarToken(String token, UserDetails userDetails) {
        final String username = extraerUsername(token);
        return (username.equals(userDetails.getUsername()) && !estaExpirado(token));
    }

    // Verifica si el token expiró
    private boolean estaExpirado(String token) {
        return extraerFechaExpiracion(token).before(new Date());
    }

    // Obtener fecha de expiración
    private Date extraerFechaExpiracion(String token) {
        return extraerReclamacion(token, Claims::getExpiration);
    }

    // Obtener cualquier dato del token
    private <T> T extraerReclamacion(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
        return claimsResolver.apply(claims);
    }
}
