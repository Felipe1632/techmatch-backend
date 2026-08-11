package com.techmatch.backend.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

public class EspecialidadeProfissionalDTO {

    @JsonProperty("profissional_id")
    @JsonAlias({"profissionalId"})
    private Long profissionalId;

    @JsonProperty("especialidade_id")
    @JsonAlias({"especialidadeId"})
    private Long especialidadeId;

    private String nivel;

    @JsonProperty("anos_experiencia")
    @JsonAlias({"anosExperiencia"})
    private Integer anosExperiencia;

    public EspecialidadeProfissionalDTO() {
    }

    public EspecialidadeProfissionalDTO(Long profissionalId, Long especialidadeId, String nivel, Integer anosExperiencia) {
        this.profissionalId = profissionalId;
        this.especialidadeId = especialidadeId;
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
}