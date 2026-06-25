/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.techmatch.backend.controller;

import com.techmatch.backend.model.ProfissionalDTO;
import com.techmatch.backend.model.UserRequestDTO;
import com.techmatch.backend.service.ProfissionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Usuario
 */
@RestController
@RequestMapping("/api/auth")
public class ProfissionalController {
    @Autowired
    private ProfissionalService service;
    
    @PostMapping("/registrar")
    public String registrar(@RequestBody ProfissionalDTO user) { 
          service.register(user);
          return "Cadastro Feito com sucesso!";      
    }
    
    @PostMapping("/logar")
    public String login (@RequestBody UserRequestDTO user){
        return service.logar(user);        
    } 
}
