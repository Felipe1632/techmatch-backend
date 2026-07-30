/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.techmatch.backend.service;

import com.techmatch.backend.model.Profissional;
import com.techmatch.backend.model.UserRequestDTO;
import com.techmatch.backend.repository.ProfissionalRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.util.Date;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author Usuario
 */
@Service
public class TokenService {
    @Value("${api.security.token.secret}")
    private String secret;
    
    private ProfissionalRepository repository;
    
    UserRequestDTO userRequest = new UserRequestDTO();
    
    public SecretKey getKeySign() {
        byte[] keyBytes = Decoders.BASE64.decode(this.secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }
    
    public String gerarToken(String email, String senha) {
       if(
           (
            email.equals("") || 
               senha.equals(""))
         ){
           throw new ResponseStatusException(HttpStatusCode.valueOf(400),
           "Um ou mais campos faltantes");
       }
        System.out.println("Email:" + email);
       return Jwts.builder()
               .subject(email)
               .claim("email", email)
               .claim("senha", senha)
               .issuedAt(new Date())
               .expiration(new Date(System.currentTimeMillis() + 3000000))
               .signWith(this.getKeySign())
               .compact();
    }
    public Profissional extrairClaim(String token) {
        Claims claims = Jwts.parser()
                .verifyWith(this.getKeySign())
                .build()
                .parseSignedClaims(token)
                .getPayload();
        
        Profissional user = new Profissional();
        user.setEmail(claims.get("email", String.class));
        return user;
    }
    
    public boolean validarToken(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(getKeySign())
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }
}
