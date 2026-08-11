package com.techmatch.backend.model;

import java.io.Serializable;
import java.util.Objects;

public class EspecialidadeProfissionalId implements Serializable {

    // Os nomes aqui devem ser exatamente iguais aos atributos na entidade
    private Long profissional;
    private Long especialidade;

    public EspecialidadeProfissionalId() {}

    public EspecialidadeProfissionalId(Long profissional, Long especialidade) {
        this.profissional = profissional;
        this.especialidade = especialidade;
    }

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