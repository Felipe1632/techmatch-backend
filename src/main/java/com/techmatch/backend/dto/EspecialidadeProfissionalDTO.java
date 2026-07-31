/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.techmatch.backend.dto;

/**
 *
 * @author Aluno
 */
public class EspecialidadeProfissionalDTO {
    public Long profissional_id;
    public Long especialidade_id;
    public String nivel;
    public int anos_experiencia;

    public EspecialidadeProfissionalDTO() {
    }

    public EspecialidadeProfissionalDTO(Long profissional_id, Long especialidade_id, String nivel, int anos_experiencia) {
        this.profissional_id = profissional_id;
        this.especialidade_id = especialidade_id;
        this.nivel = nivel;
        this.anos_experiencia = anos_experiencia;
    }

    public Long getProfissional_id() {
        return profissional_id;
    }

    public void setProfissional_id(Long profissional_id) {
        this.profissional_id = profissional_id;
    }

    public Long getEspecialidade_id() {
        return especialidade_id;
    }

    public void setEspecialidade_id(Long especialidade_id) {
        this.especialidade_id = especialidade_id;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public int getAnos_experiencia() {
        return anos_experiencia;
    }

    public void setAnos_experiencia(int anos_experiencia) {
        this.anos_experiencia = anos_experiencia;
    }
    
    
}
