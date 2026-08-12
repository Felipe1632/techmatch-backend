package com.techmatch.backend.controller;

import com.techmatch.backend.dto.EspecialidadeProfissionalDTO;
import com.techmatch.backend.dto.ProfissionalRequest;
import com.techmatch.backend.dto.UserRequestDTO;
import com.techmatch.backend.service.ProfissionalService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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
        System.out.println("Nome: " + userRequest.getValorHora());
        return service.register(userRequest);
    }

    @PostMapping("/login")
    public String login(@RequestBody UserRequestDTO user) {
        return service.logar(user);
    }

    @PostMapping("/especialidades")
    public ResponseEntity<String> salvarEspecialidades(@RequestBody EspecialidadeProfissionalDTO dto) {
        System.out.println("Profissional ID: " + dto.getProfissionalId());
        System.out.println("Especialidade ID: " + dto.getEspecialidadeId());

        String resposta = service.salvarEspecialidades(List.of(dto));
        return ResponseEntity.ok(resposta);
    }

    @GetMapping("/buscar")
    public ProfissionalRequest buscarPorEmail(@RequestParam String email) {
        return service.buscarPorEmail(email);
    }
       
    @DeleteMapping("/especialidades/remover")
    public ResponseEntity<Void> removerEspecialidade(@RequestParam("profissionalId") Long profissionalId, 
                                                     @RequestParam("especialidadeId") Long especialidadeId) {
        System.out.println(">>> DEBUG 3: Backend REST recebeu o DELETE do Frontend");
        service.removerEspecialidade(profissionalId, especialidadeId);
        return ResponseEntity.ok().build();
    }
}
