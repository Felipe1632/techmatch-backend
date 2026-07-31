/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.techmatch.backend.controller;

import com.techmatch.backend.model.EspecialidadeProfissional;
import com.techmatch.backend.model.ProfissionalConfiguracao;
import com.techmatch.backend.model.Profissional;
import com.techmatch.backend.dto.ProfissionalRequest;
import com.techmatch.backend.dto.UserRequestDTO;
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
    public String registrar(@RequestBody ProfissionalRequest userRequest) { 
          return service.register(userRequest);
    }
    
//    @PostMapping("/registrar/configuracao")
//    public String salvarConfiguracao(@RequestBody ProfissionalConfiguracao config){
//        return service.salvarConfiguracao(config);
//    }
//    
//    @PostMapping("/registrar/especialidades")
//    public String salvarEspecialidade(@RequestBody EspecialidadeProfissional especialidades){
//        return service.salvarEspecialidades(especialidades);
//    }
    
    @PostMapping("/logar")
    public String login (@RequestBody UserRequestDTO user){
        return service.logar(user);        
    }
    
    
}
