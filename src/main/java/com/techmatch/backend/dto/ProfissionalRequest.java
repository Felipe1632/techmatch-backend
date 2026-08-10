/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.techmatch.backend.dto;

import com.techmatch.backend.model.EspecialidadeProfissional;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author Aluno
 */
public class ProfissionalRequest {
    //profissional
    public Long id;
    
    public String nome;
    public String email;
    public String senha;
    public String cpf;
    public String telefone;
    public String cidade;
    public String estado;
    public BigDecimal valorHora;
    private Integer RaioAtendimentoKm;
    private List<EspecialidadeProfissional> especialidades;
    
    public ProfissionalRequest() {
    }

    public ProfissionalRequest(Long id, String nome, String email, String senha, String cpf, String telefone, String cidade, String estado, BigDecimal valorHora, Integer RaioAtendimentoKm, List<EspecialidadeProfissional> especialidades) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.cpf = cpf;
        this.telefone = telefone;
        this.cidade = cidade;
        this.estado = estado;
        this.valorHora = valorHora;
        this.RaioAtendimentoKm = RaioAtendimentoKm;
        this.especialidades = especialidades;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public BigDecimal getValorHora() {
        return valorHora;
    }

    public void setValorHora(BigDecimal valorHora) {
        this.valorHora = valorHora;
    }

    public Integer getRaioAtendimentoKm() {
        return RaioAtendimentoKm;
    }

    public void setRaioAtendimentoKm(Integer RaioAtendimentoKm) {
        this.RaioAtendimentoKm = RaioAtendimentoKm;
    }

    public List<EspecialidadeProfissional> getEspecialidades() {
        return especialidades;
    }

    public void setEspecialidades(List<EspecialidadeProfissional> especialidades) {
        this.especialidades = especialidades;
    }

 
}
