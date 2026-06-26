/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.techmatch.backend.service;

import com.techmatch.backend.model.EspecialidadeProfissionalDTO;
import com.techmatch.backend.model.ProfissionalDTO;
import com.techmatch.backend.model.ProfissionalRequest;
import com.techmatch.backend.model.UserRequestDTO;
import com.techmatch.backend.repository.ProfissionalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author Usuario
 */
@Service
public class ProfissionalService {
  
    @Autowired
    private ProfissionalRepository repository;
    
    @Autowired
    private TokenService tokenservice;
    
        public void register(ProfissionalRequest userRequest) {
        String mensagem = "";
        if(userRequest.getNome().equals("")) {
            mensagem = "Nome não preenchido";
        } else if (userRequest.getEmail().equals("")){
            mensagem = "Email não preenchido";
        } else if (userRequest.getSenha().equals("")){
            mensagem = "Senha não preenchida";
        } else if (userRequest.getCpf().equals("")){
            mensagem = "CPF não preenchido";
        } else if (userRequest.getTelefone().equals("")){
            mensagem = "Telefone não preenchido";
        } else if (userRequest.getCidade().equals("")){
            mensagem = "Cidade não preenchida";
        } else if (userRequest.getEstado().equals("")){
            mensagem = "Estado não preenchido";
        }
      
        if(!mensagem.equals("")) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(400), mensagem);
                }
        repository.register(userRequest);
    }
        public String logar(UserRequestDTO user){
        String mensagem = "";
        if(user.getEmail().equals("")){
            mensagem = "Email não preenchido";
        } else if(user.getSenha().equals("")){
            mensagem = "Senha não preenchida";
        }
        
        if(!mensagem.equals("")){
            throw new ResponseStatusException(HttpStatusCode.valueOf(400), mensagem);
        }
        ProfissionalDTO dadosLogado = repository.logar(user.getEmail(), user.getSenha());
            return tokenservice.gerarToken(dadosLogado);
        }
}
