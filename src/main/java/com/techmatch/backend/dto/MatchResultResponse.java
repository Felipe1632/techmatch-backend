/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.techmatch.backend.dto;

/**
 *
 * @author Usuario
 */
public class MatchResultResponse {
    private Long id;
    private Long profissionalId;
    private String profissionalNome;
    private Double valorHora;
    private Double scoreTotal;
    private String statusResposta;

    public MatchResultResponse() {
    }

    public MatchResultResponse(Long id, Long profissionalId, String profissionalNome, Double valorHora, Double scoreTotal, String statusResposta) {
        this.id = id;
        this.profissionalId = profissionalId;
        this.profissionalNome = profissionalNome;
        this.valorHora = valorHora;
        this.scoreTotal = scoreTotal;
        this.statusResposta = statusResposta;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProfissionalId() {
        return profissionalId;
    }

    public void setProfissionalId(Long profissionalId) {
        this.profissionalId = profissionalId;
    }

    public String getProfissionalNome() {
        return profissionalNome;
    }

    public void setProfissionalNome(String profissionalNome) {
        this.profissionalNome = profissionalNome;
    }

    public Double getValorHora() {
        return valorHora;
    }

    public void setValorHora(Double valorHora) {
        this.valorHora = valorHora;
    }

    public Double getScoreTotal() {
        return scoreTotal;
    }

    public void setScoreTotal(Double scoreTotal) {
        this.scoreTotal = scoreTotal;
    }

    public String getStatusResposta() {
        return statusResposta;
    }

    public void setStatusResposta(String statusResposta) {
        this.statusResposta = statusResposta;
    }
    
    
}
