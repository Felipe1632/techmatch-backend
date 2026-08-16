/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.techmatch.backend.controller;

/**
 *
 * @author Usuario
 */
import com.techmatch.backend.dto.ChamadoRequest;
import com.techmatch.backend.dto.MatchResultResponse;
import com.techmatch.backend.dto.MinhaCandidatura;
import com.techmatch.backend.dto.ChamadoResponse;
import com.techmatch.backend.service.ChamadoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/techmatch/chamados")
public class ChamadoController {

    @Autowired
    private ChamadoService service;

    @PostMapping("/abrir")
    public ResponseEntity<String> abrir(@RequestBody ChamadoRequest dto) {
        String resposta = service.abrirChamado(dto);
        return ResponseEntity.ok(resposta);
    }

    @GetMapping("/abertos")
    public List<ChamadoResponse> listarAbertos() {
        return service.listarAbertos();
    }

    @PostMapping("/{chamadoId}/candidatar/{profissionalId}")
    public ResponseEntity<String> candidatar(@PathVariable Long chamadoId, @PathVariable Long profissionalId) {
        return ResponseEntity.ok(service.candidatar(chamadoId, profissionalId));
    }

    @GetMapping("/{chamadoId}/candidatos")
    public List<MatchResultResponse> listarCandidatos(@PathVariable Long chamadoId) {
        return service.listarCandidatos(chamadoId);
    }

    @PostMapping("/{chamadoId}/aceitar/{matchResultId}")
    public ResponseEntity<String> aceitar(@PathVariable Long chamadoId, @PathVariable Long matchResultId) {
        return ResponseEntity.ok(service.aceitarCandidato(chamadoId, matchResultId));
    }

    @PostMapping("/recusar/{matchResultId}")
    public ResponseEntity<String> recusar(@PathVariable Long matchResultId) {
        return ResponseEntity.ok(service.recusarCandidato(matchResultId));
    }
    
    @GetMapping("/empresa/{empresaId}")
    public List<ChamadoResponse> listarPorEmpresa(@PathVariable Long empresaId) {
    return service.listarPorEmpresa(empresaId);
}
    
    @GetMapping("/profissional/{profissionalId}/candidaturas")
    public List<MinhaCandidatura> listarMinhasCandidaturas(@PathVariable Long profissionalId) {
    return service.listarMinhasCandidaturas(profissionalId);
}
}
