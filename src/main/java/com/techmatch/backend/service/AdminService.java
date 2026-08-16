/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.techmatch.backend.service;

import com.techmatch.backend.dto.UserRequestDTO;
import com.techmatch.backend.model.Admin;
import com.techmatch.backend.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AdminService {

    @Autowired
    private AdminRepository repository;

    @Autowired
    private TokenService tokenservice;

    public String login(UserRequestDTO user) {
        String mensagem = "";
        if (user.getEmail().equals("")) {
            mensagem = "Email não preenchido";
        } else if (user.getSenha().equals("")) {
            mensagem = "Senha não preenchida";
        }
        if (!mensagem.equals("")) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(400), mensagem);
        }

        Admin admin = repository.findByEmailAndSenha(user.getEmail(), user.getSenha());
        if (admin == null) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(401), "Credenciais inválidas");
        }
        return tokenservice.gerarToken(admin.getId(), user.getEmail(), user.getSenha());
    }
}