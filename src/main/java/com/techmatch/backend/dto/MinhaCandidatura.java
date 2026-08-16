/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.techmatch.backend.dto;

/**
 *
 * @author Usuario
 */
public class MinhaCandidatura {
    private Long chamadoId;
    private String descricao;
    private String empresaNome;
    private String especialidadeNome;
    private String statusChamado;
    private String statusResposta;
    private Double scoreTotal;

    public MinhaCandidatura() {
    }

    public MinhaCandidatura(Long chamadoId, String descricao, String empresaNome, String especialidadeNome, String statusChamado, String statusResposta, Double scoreTotal) {
        this.chamadoId = chamadoId;
        this.descricao = descricao;
        this.empresaNome = empresaNome;
        this.especialidadeNome = especialidadeNome;
        this.statusChamado = statusChamado;
        this.statusResposta = statusResposta;
        this.scoreTotal = scoreTotal;
    }

    public Long getChamadoId() {
        return chamadoId;
    }

    public void setChamadoId(Long chamadoId) {
        this.chamadoId = chamadoId;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getEmpresaNome() {
        return empresaNome;
    }

    public void setEmpresaNome(String empresaNome) {
        this.empresaNome = empresaNome;
    }

    public String getEspecialidadeNome() {
        return especialidadeNome;
    }

    public void setEspecialidadeNome(String especialidadeNome) {
        this.especialidadeNome = especialidadeNome;
    }

    public String getStatusChamado() {
        return statusChamado;
    }

    public void setStatusChamado(String statusChamado) {
        this.statusChamado = statusChamado;
    }

    public String getStatusResposta() {
        return statusResposta;
    }

    public void setStatusResposta(String statusResposta) {
        this.statusResposta = statusResposta;
    }

    public Double getScoreTotal() {
        return scoreTotal;
    }

    public void setScoreTotal(Double scoreTotal) {
        this.scoreTotal = scoreTotal;
    }
    
    
}
