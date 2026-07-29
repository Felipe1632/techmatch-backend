/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.techmatch.backend.model;

/**
 *
 * @author Aluno
 */
public class ProfissionalConfiguracaoDTO {
    public Long id_profissional;
    public Integer raio_atendimento_km;


    public ProfissionalConfiguracaoDTO() {
    }

    public ProfissionalConfiguracaoDTO(Long id_profissional, Integer raio_atendimento_km) {
        this.id_profissional = id_profissional;
        this.raio_atendimento_km = raio_atendimento_km;

    }

    public Long getId_profissional() {
        return id_profissional;
    }

    public void setId_profissional(Long id_profissional) {
        this.id_profissional = id_profissional;
    }

    public Integer getRaio_atendimento_km() {
        return raio_atendimento_km;
    }

    public void setRaio_atendimento_km(Integer raio_atendimento_km) {
        this.raio_atendimento_km = raio_atendimento_km;
    }
   
}
