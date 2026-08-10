/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.techmatch.backend.controller;

import com.techmatch.backend.dto.EspecialidadeProfissionalDTO;
import com.techmatch.backend.dto.ProfissionalRequest;
import com.techmatch.backend.dto.UserRequestDTO;
import com.techmatch.backend.service.ProfissionalService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/techmatch/profissionais")
public class ProfissionalController {

    @Autowired
    private ProfissionalService service;

    @PostMapping("/registrar")
    public String registrar(@RequestBody ProfissionalRequest userRequest) {
        System.out.println("Nome" + userRequest.getValorHora());
        return service.register(userRequest);
    }

    @PostMapping("/login")
    public String login(@RequestBody UserRequestDTO user) {
        return service.logar(user);
    }

    @PostMapping("/registrar/especialidades")
    public String registrarEspecialidades(@RequestParam List<EspecialidadeProfissionalDTO> especialidades) {
        return service.salvarEspecialidades(especialidades);
    }
}
