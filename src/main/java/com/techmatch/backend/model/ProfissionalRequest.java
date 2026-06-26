/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.techmatch.backend.model;

import org.springframework.stereotype.Service;

/**
 *
 * @author Aluno
 */
public class ProfissionalRequest {
    //profissional
    public String nome;
    public String email;
    public String senha;
    public String cpf;
    public String telefone;
    public String cidade;
    public String estado;
    public Double valor_hora;

    public ProfissionalRequest() {
    }

    public ProfissionalRequest(String nome, String email, String senha, String cpf, String telefone, String cidade, String estado, Double valor_hora) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.cpf = cpf;
        this.telefone = telefone;
        this.cidade = cidade;
        this.estado = estado;
        this.valor_hora = valor_hora;
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

    public Double getValor_hora() {
        return valor_hora;
    }

    public void setValor_hora(Double valor_hora) {
        this.valor_hora = valor_hora;
    }

    
}
