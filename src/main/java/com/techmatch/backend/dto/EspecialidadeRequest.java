/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.techmatch.backend.dto;

/**
 *
 * @author Usuario
 */
 
public class EspecialidadeRequest {
    private String nome;
    private String categoria;
    private Double valorHoraMinimo;

    public EspecialidadeRequest() {
    }

    public EspecialidadeRequest(String nome, String categoria, Double valorHoraMinimo) {
        this.nome = nome;
        this.categoria = categoria;
        this.valorHoraMinimo = valorHoraMinimo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Double getValorHoraMinimo() {
        return valorHoraMinimo;
    }

    public void setValorHoraMinimo(Double valorHoraMinimo) {
        this.valorHoraMinimo = valorHoraMinimo;
    }
 
    
}
