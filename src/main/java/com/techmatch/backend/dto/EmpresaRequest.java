/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.techmatch.backend.dto;

/**
 *
 * @author Aluno
 */
public class EmpresaRequest {
    private String email;
    private String senha;
    private String cnpj;
    private String telefone;
    private String cidade;
    private String estado;

    public EmpresaRequest() {
    }

    public EmpresaRequest(String email, String senha, String cnpj, String telefone, String cidade, String estado) {
        this.email = email;
        this.senha = senha;
        this.cnpj = cnpj;
        this.telefone = telefone;
        this.cidade = cidade;
        this.estado = estado;
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

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
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
    
    
}
