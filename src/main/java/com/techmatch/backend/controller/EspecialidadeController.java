/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.techmatch.backend.controller;

import com.techmatch.backend.dto.EspecialidadeRequest;
import com.techmatch.backend.model.Especialidade;
import com.techmatch.backend.repository.EspecialidadeRepository;
import com.techmatch.backend.service.EspecialidadeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    
    @Autowired
    private EspecialidadeService service;

    @GetMapping
    public List<Especialidade> listarTodas() {
        return repository.findAll();
    }
    
    @PostMapping("/cadastrar")
    public ResponseEntity<String> cadastrar(@RequestBody EspecialidadeRequest dto) {
    return ResponseEntity.ok(service.cadastrar(dto));
}
}
