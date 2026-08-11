package com.techmatch.backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "profissional_especialidade")
@IdClass(EspecialidadeProfissionalId.class)
public class EspecialidadeProfissional {

    // ATENÇÃO: Não coloque 'private Long id;' aqui!

    @Id
    @ManyToOne
    @JoinColumn(name = "profissional_id")
    private Profissional profissional;

    @Id
    @ManyToOne
    @JoinColumn(name = "especialidade_id")
    private Especialidade especialidade;

    private String nivel;

    @Column(name = "anos_experiencia")
    private Integer anos_experiencia;

    public EspecialidadeProfissional() {}

    public Profissional getProfissional() {
        return profissional;
    }

    public void setProfissional(Profissional profissional) {
        this.profissional = profissional;
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

    public Integer getAnos_experiencia() {
        return anos_experiencia;
    }

    public void setAnos_experiencia(Integer anos_experiencia) {
        this.anos_experiencia = anos_experiencia;
    }
}