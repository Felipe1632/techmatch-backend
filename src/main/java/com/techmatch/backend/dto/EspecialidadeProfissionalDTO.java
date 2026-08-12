package com.techmatch.backend.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.techmatch.backend.model.Especialidade;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class EspecialidadeProfissionalDTO {

    @JsonProperty("profissional_id")
    @JsonAlias({"profissionalId"})
    private Long profissionalId;

    @JsonProperty("especialidade_id")
    @JsonAlias({"especialidadeId"})
    private Long especialidadeId;

    @ManyToOne
    @JoinColumn(name = "especialidade_id")
    private Especialidade especialidade;
    
    private String nivel;

    @JsonProperty("anos_experiencia")
    @JsonAlias({"anosExperiencia"})
    private Integer anosExperiencia;

    public EspecialidadeProfissionalDTO() {
    }

    public EspecialidadeProfissionalDTO(Long profissionalId, Long especialidadeId, Especialidade especialidade, String nivel, Integer anosExperiencia) {
        this.profissionalId = profissionalId;
        this.especialidadeId = especialidadeId;
        this.especialidade = especialidade;
        this.nivel = nivel;
        this.anosExperiencia = anosExperiencia;
    }

    public Long getProfissionalId() {
        return profissionalId;
    }

    public void setProfissionalId(Long profissionalId) {
        this.profissionalId = profissionalId;
    }

    public Long getEspecialidadeId() {
        return especialidadeId;
    }

    public void setEspecialidadeId(Long especialidadeId) {
        this.especialidadeId = especialidadeId;
    }

    public Especialidade getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(Especialidade especialidade) {
        this.especialidade = especialidade;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public Integer getAnosExperiencia() {
        return anosExperiencia;
    }

    public void setAnosExperiencia(Integer anosExperiencia) {
        this.anosExperiencia = anosExperiencia;
    }

    public String getNomeEspecialidade() {
        return this.especialidade != null ? this.especialidade.getNome() : null;
    }
}