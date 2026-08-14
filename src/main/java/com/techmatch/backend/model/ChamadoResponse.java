/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.techmatch.backend.model;

/**
 *
 * @author Usuario
 */
public class ChamadoResponse {
    private Long id;
    private String descricao;
    private String urgencia;
    private String status;
    private Double orcamentoMaximo;
    private String cidade;
    private String estado;
    private String especialidadeNome;
    private String empresaNome;

    public ChamadoResponse() {
    }

    public ChamadoResponse(Long id, String descricao, String urgencia, String status, Double orcamentoMaximo, String cidade, String estado, String especialidadeNome, String empresaNome) {
        this.id = id;
        this.descricao = descricao;
        this.urgencia = urgencia;
        this.status = status;
        this.orcamentoMaximo = orcamentoMaximo;
        this.cidade = cidade;
        this.estado = estado;
        this.especialidadeNome = especialidadeNome;
        this.empresaNome = empresaNome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getUrgencia() {
        return urgencia;
    }

    public void setUrgencia(String urgencia) {
        this.urgencia = urgencia;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getOrcamentoMaximo() {
        return orcamentoMaximo;
    }

    public void setOrcamentoMaximo(Double orcamentoMaximo) {
        this.orcamentoMaximo = orcamentoMaximo;
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

    public String getEspecialidadeNome() {
        return especialidadeNome;
    }

    public void setEspecialidadeNome(String especialidadeNome) {
        this.especialidadeNome = especialidadeNome;
    }

    public String getEmpresaNome() {
        return empresaNome;
    }

    public void setEmpresaNome(String empresaNome) {
        this.empresaNome = empresaNome;
    }
    
    
}
