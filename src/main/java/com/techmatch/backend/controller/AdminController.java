/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.techmatch.backend.controller;

import com.techmatch.backend.dto.UserRequestDTO;
import com.techmatch.backend.service.AdminService;
import com.techmatch.backend.service.ProfissionalService;
import com.techmatch.backend.service.EmpresaService;
import com.techmatch.backend.dto.ProfissionalRequest;
import com.techmatch.backend.dto.EmpresaRequest;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/techmatch/admins")
public class AdminController {

    @Autowired
    private AdminService service;

    @Autowired
    private ProfissionalService profissionalService;

    @Autowired
    private EmpresaService empresaService;

    @PostMapping("/login")
    public String login(@RequestBody UserRequestDTO user) {
        return service.login(user);
    }

    @GetMapping("/profissionais/pendentes")
    public List<ProfissionalRequest> profissionaisPendentes() {
        return profissionalService.listarPendentes();
    }

    @PostMapping("/profissionais/{id}/aprovar")
    public ResponseEntity<String> aprovarProfissional(@PathVariable Long id) {
        return ResponseEntity.ok(profissionalService.alterarStatus(id, "ativo"));
    }

    @PostMapping("/profissionais/{id}/rejeitar")
    public ResponseEntity<String> rejeitarProfissional(@PathVariable Long id) {
        return ResponseEntity.ok(profissionalService.alterarStatus(id, "suspenso"));
    }

    @GetMapping("/empresas/pendentes")
    public List<EmpresaRequest> empresasPendentes() {
        return empresaService.listarPendentes();
    }

    @PostMapping("/empresas/{id}/aprovar")
    public ResponseEntity<String> aprovarEmpresa(@PathVariable Long id) {
        return ResponseEntity.ok(empresaService.alterarStatus(id, "ativo"));
    }

    @PostMapping("/empresas/{id}/rejeitar")
    public ResponseEntity<String> rejeitarEmpresa(@PathVariable Long id) {
        return ResponseEntity.ok(empresaService.alterarStatus(id, "suspenso"));
    }
}
