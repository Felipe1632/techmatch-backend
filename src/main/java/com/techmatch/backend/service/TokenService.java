/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.techmatch.backend.service;

import com.techmatch.backend.model.ProfissionalDTO;
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
    
    public SecretKey getKeySign() {
        byte[] keyBytes = Decoders.BASE64.decode(this.secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }
    
    public String gerarToken(ProfissionalDTO user) {
       if(
           (user.getId() == null || user.getId() == 0L)  ||
            user.getNome().equals("") ||
            user.getEmail().equals("") ||
            user.getCpf() == null ||
            user.getCpf().equals("") ||
            user.getTelefone() == null || 
            user.getTelefone().equals("") ||
            user.getCidade() == null || 
            user.getCidade().equals("") ||
            user.getEstado() == null || 
            user.getEstado().equals("")
         ){
           throw new ResponseStatusException(HttpStatusCode.valueOf(400),
           "Um ou mais campos faltantes");
       }
       return Jwts.builder()
               .subject(user.getNome())
               .claim("id", user.getId())
               .claim("nome", user.getNome())
               .claim("email", user.getEmail())
               .claim("cpf", user.getCpf())
               .claim("telefone", user.getTelefone())
               .claim("cidade", user.getCidade())
               .claim("estado", user.getEstado())
               .issuedAt(new Date())
               .expiration(new Date(System.currentTimeMillis() + 3000000))
               .signWith(this.getKeySign())
               .compact();
    }
    public ProfissionalDTO extrairClaim(String token) {
        Claims claims = Jwts.parser()
                .verifyWith(this.getKeySign())
                .build()
                .parseSignedClaims(token)
                .getPayload();
        
        ProfissionalDTO user = new ProfissionalDTO();
        user.setId(claims.get("id", Long.class));
        user.setNome(claims.get("nome", String.class));
        user.setEmail(claims.get("email", String.class));
        user.setCpf(claims.get("cpf", String.class));
        user.setTelefone(claims.get("telefone", String.class));
        user.setCidade(claims.get("cidade", String.class));
        user.setEstado(claims.get("estado", String.class));
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
