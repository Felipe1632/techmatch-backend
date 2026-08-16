/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.techmatch.backend.service;

import com.techmatch.backend.dto.EspecialidadeRequest;
import com.techmatch.backend.model.Especialidade;
import com.techmatch.backend.repository.EspecialidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Usuario
 */
@Service
public class EspecialidadeService {
    
    @Autowired
    private EspecialidadeRepository especialidadeRepository;
    
    public String cadastrar(EspecialidadeRequest dto) {
    Especialidade e = new Especialidade();
    e.setNome(dto.getNome());
    e.setCategoria(dto.getCategoria());
    e.setValorHoraMinimo(dto.getValorHoraMinimo());
    especialidadeRepository.save(e);
    return "Especialidade cadastrada com sucesso.";
}
}
