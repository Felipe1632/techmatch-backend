/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.techmatch.backend.service;

import com.techmatch.backend.dto.EmpresaRequest;
import com.techmatch.backend.model.Empresa;
import com.techmatch.backend.repository.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author Aluno
 */
@Service
public class EmpresaService {
    
    @Autowired
    private EmpresaRepository repository;
    
    @Autowired
    private TokenService tokenservice;
    
    public String register(EmpresaRequest empresa){
    String mensagem = "";
    if(empresa.getEmail().equals("")){
        mensagem = "Email não preenchido!";
    }
    else if(empresa.getSenha().equals("")){
        mensagem = "Senha não preenchida!";
    }
    else if(empresa.getCnpj().equals("")){
        mensagem = "Cnpj não preenchido!";
    }
    else if(empresa.getTelefone().equals("")){
        mensagem = "Telefone não preenchido!";
    }
    else if(empresa.getCidade().equals("")){
        mensagem = "Cidade não preenchida!";
    }
    else if(empresa.getEstado().equals("")){
        mensagem = "Estado não preenchido!";
    }
    
    if (!mensagem.equals("")) {
                throw new ResponseStatusException(HttpStatusCode.valueOf(400), mensagem);
            }
    
    Empresa p = new Empresa();
    p.setEmail(mensagem);
    
    return "st";
    
    }
}
