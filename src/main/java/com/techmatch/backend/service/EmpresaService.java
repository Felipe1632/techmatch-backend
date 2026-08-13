/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.techmatch.backend.service;

import com.techmatch.backend.dto.EmpresaRequest;
import com.techmatch.backend.dto.UserRequestDTO;
import com.techmatch.backend.model.Empresa;
import com.techmatch.backend.repository.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author Aluno
 */
@Service
public class EmpresaService {
    
    @Autowired
    private EmpresaRepository repositoryEmpresa;
    
    @Autowired
    private TokenService tokenservice;
    
    public String register(EmpresaRequest empresa){
    String mensagem = "";
    
    if(empresa.getNome().equals("")){
        mensagem = "Nome não preenchido!";
    }    
    else if(empresa.getEmail().equals("")){
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
//    else if(empresa.getStatus().equals("")){
//        mensagem = "Status não preenchido";
//    }
    
    if (!mensagem.equals("")) {
                throw new ResponseStatusException(HttpStatusCode.valueOf(400), mensagem);
            }
    
    Empresa p = new Empresa();
    p.setNome(empresa.getNome());
    p.setEmail(empresa.getEmail());
    p.setSenha(empresa.getSenha());
    p.setCnpj(empresa.getCnpj());
    p.setTelefone(empresa.getTelefone());
    p.setCidade(empresa.getCidade());
    p.setEstado(empresa.getEstado());
    p.setStatus("pendente");
    
    Empresa salvo = repositoryEmpresa.save(p);
    
    return "Empresa cadastrada com sucesso!";
    
    }
    
    public String login(UserRequestDTO empresa){
        String mensagem = "";
        if(empresa.getEmail().equals("")){
            mensagem = "Email não preenchido";
        } else if(empresa.getSenha().equals("")){
            mensagem = "Senha não preenchida";
        }
        
        if(!mensagem.equals("")){
            throw new ResponseStatusException(HttpStatusCode.valueOf(400), mensagem);
        }
        
        Empresa empresaEncontrada = repositoryEmpresa.findByEmailAndSenha(empresa.getEmail(), empresa.getSenha());
        return tokenservice.gerarToken(empresaEncontrada.getId() ,empresa.getEmail(), empresa.getSenha());
    }
}
