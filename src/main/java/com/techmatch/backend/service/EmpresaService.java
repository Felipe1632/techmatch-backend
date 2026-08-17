/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.techmatch.backend.service;

import com.techmatch.backend.dto.EmpresaRequest;
import com.techmatch.backend.dto.UserRequestDTO;
import com.techmatch.backend.model.Empresa;
import com.techmatch.backend.repository.EmpresaRepository;
import java.util.ArrayList;
import java.util.List;
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
        if (empresa.getEmail().equals("")) {
            mensagem = "Email não preenchido";
        } else if (empresa.getSenha().equals("")) {
            mensagem = "Senha não preenchida";
        } if (!mensagem.equals("")) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(400), mensagem);
        }

        Empresa empresaEncontrada = repositoryEmpresa.findByEmailAndSenha(empresa.getEmail(), empresa.getSenha());

        if (empresaEncontrada == null) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(401), "Credenciais inválidas");
        } if ("pendente".equals(empresaEncontrada.getStatus())) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(403), "Seu cadastro ainda está em análise pelo administrador.");
        } if ("suspenso".equals(empresaEncontrada.getStatus())) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(403), "Seu cadastro foi rejeitado ou suspenso.");
        }
        return tokenservice.gerarToken(empresaEncontrada.getId(), empresa.getEmail(), empresa.getSenha());
    }
        public EmpresaRequest buscarPorEmail(String email) {
        Empresa empresa = repositoryEmpresa.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Empresa não encontrada"));

        EmpresaRequest dto = new EmpresaRequest();
        dto.setId(empresa.getId());
        dto.setNome(empresa.getNome());
        dto.setEmail(empresa.getEmail());
        dto.setTelefone(empresa.getTelefone());
        dto.setCidade(empresa.getCidade());
        dto.setEstado(empresa.getEstado());

        return dto;
    }
    
    public List<EmpresaRequest> listarPendentes() {
    List<Empresa> pendentes = repositoryEmpresa.findByStatus("pendente");
    List<EmpresaRequest> resultado = new ArrayList<>();

    for (Empresa e : pendentes) {
        EmpresaRequest dto = new EmpresaRequest();
        dto.setId(e.getId());
        dto.setNome(e.getNome());
        dto.setEmail(e.getEmail());
        dto.setCnpj(e.getCnpj());
        dto.setTelefone(e.getTelefone());
        dto.setCidade(e.getCidade());
        dto.setEstado(e.getEstado());
        dto.setStatus(e.getStatus());
        resultado.add(dto);
    }

    return resultado;
}

    public String alterarStatus(Long id, String novoStatus) {
    Empresa empresa = repositoryEmpresa.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatusCode.valueOf(404), "Empresa não encontrada."));
    empresa.setStatus(novoStatus);
    repositoryEmpresa.save(empresa);
    return "Status atualizado para: " + novoStatus;
    }
    
    public List<EmpresaRequest> listarTodas() {
    List<Empresa> todas = repositoryEmpresa.findAll();
    List<EmpresaRequest> resultado = new ArrayList<>();

    for (Empresa e : todas) {
        EmpresaRequest dto = new EmpresaRequest();
        dto.setId(e.getId());
        dto.setNome(e.getNome());
        dto.setEmail(e.getEmail());
        dto.setCnpj(e.getCnpj());
        dto.setTelefone(e.getTelefone());
        dto.setCidade(e.getCidade());
        dto.setEstado(e.getEstado());
        dto.setStatus(e.getStatus());
        resultado.add(dto);
    }

    return resultado;
}
}
