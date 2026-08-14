/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.techmatch.backend.controller;

import com.techmatch.backend.dto.EmpresaRequest;
import com.techmatch.backend.dto.UserRequestDTO;
import com.techmatch.backend.service.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Aluno
 */
@RestController
@RequestMapping("/techmatch/empresas")
public class EmpresaController {
    
    @Autowired
    private EmpresaService service;
    
    @PostMapping("/registrar")
    public String register(@RequestBody EmpresaRequest empresa){
        return service.register(empresa);
    }
    
    @PostMapping("/login")
    public String login(@RequestBody UserRequestDTO empresa){
        return service.login(empresa);
    }
    
    @GetMapping("/buscar")
    public EmpresaRequest buscarPorEmail(@RequestParam String email) {
    return service.buscarPorEmail(email);
}
}
