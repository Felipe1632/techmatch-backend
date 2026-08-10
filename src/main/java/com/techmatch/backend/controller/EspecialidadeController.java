/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.techmatch.backend.controller;

import com.techmatch.backend.model.Especialidade;
import com.techmatch.backend.repository.EspecialidadeRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Aluno
 */
@RestController
@RequestMapping("/techmatch/especialidades")
public class EspecialidadeController {
    
    @Autowired
    private EspecialidadeRepository repository;

    @GetMapping
    public List<Especialidade> listarTodas() {
        return repository.findAll();
    }
}
