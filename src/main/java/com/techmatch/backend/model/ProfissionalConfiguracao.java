/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.techmatch.backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;

/**
 *
 * @author Aluno
 */
@Entity
@Table(name = "profissional_configuracao")
public class ProfissionalConfiguracao {
    
    @Id
    @Column(name = "profissional_id")
    private Long profissionalId;

    @OneToOne
    @MapsId
    @JoinColumn(name = "profissional_id")
    private Profissional profissional;

    @Column(name = "raio_atendimento_km", nullable = false)
    private Integer raioAtendimentoKm = 20;

    @Column(nullable = false, precision = 5, scale = 2)
    private BigDecimal score = new BigDecimal("100.00");

    @Column(nullable = false)
    private Boolean disponivel = true;

    public Long getProfissionalId() {
        return profissionalId;
    }

    public void setProfissionalId(Long profissionalId) {
        this.profissionalId = profissionalId;
    }

    public Profissional getProfissional() {
        return profissional;
    }

    public void setProfissional(Profissional profissional) {
        this.profissional = profissional;
    }

    public Integer getRaioAtendimentoKm() {
        return raioAtendimentoKm;
    }

    public void setRaioAtendimentoKm(Integer raioAtendimentoKm) {
        this.raioAtendimentoKm = raioAtendimentoKm;
    }

    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }

    public Boolean getDisponivel() {
        return disponivel;
    }

    public void setDisponivel(Boolean disponivel) {
        this.disponivel = disponivel;
    }
    
    
}
