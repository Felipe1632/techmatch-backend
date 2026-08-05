/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.techmatch.backend.model;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author Aluno
 */
public class EspecialidadeProfissionalId implements Serializable {
    private Long profissional;
    private Long especialidade;

    public EspecialidadeProfissionalId() {}

    public EspecialidadeProfissionalId(Long profissional, Long especialidade) {
        this.profissional = profissional;
        this.especialidade = especialidade;
    }

    // O JPA exige equals e hashCode para chaves compostas
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EspecialidadeProfissionalId that = (EspecialidadeProfissionalId) o;
        return Objects.equals(profissional, that.profissional) && 
               Objects.equals(especialidade, that.especialidade);
    }

    @Override
    public int hashCode() {
        return Objects.hash(profissional, especialidade);
    }
}

