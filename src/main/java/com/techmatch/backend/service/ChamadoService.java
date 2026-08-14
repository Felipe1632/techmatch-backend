/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.techmatch.backend.service;

import org.springframework.stereotype.Service;

/**
 *
 * @author Usuario
 */

import com.techmatch.backend.dto.ChamadoRequest;
import com.techmatch.backend.dto.MatchResultResponse;
import com.techmatch.backend.model.Chamado;
import com.techmatch.backend.model.ChamadoResponse;
import com.techmatch.backend.model.Empresa;
import com.techmatch.backend.model.Especialidade;
import com.techmatch.backend.model.MatchResult;
import com.techmatch.backend.model.Profissional;
import com.techmatch.backend.model.ProfissionalConfiguracao;
import com.techmatch.backend.repository.ChamadoRepository;
import com.techmatch.backend.repository.EmpresaRepository;
import com.techmatch.backend.repository.EspecialidadeRepository;
import com.techmatch.backend.repository.MatchResultRepository;
import com.techmatch.backend.repository.ProfissionalConfiguracaoRepository;
import com.techmatch.backend.repository.ProfissionalRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ChamadoService {

    @Autowired
    private ChamadoRepository repository;

    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private EspecialidadeRepository especialidadeRepository;
    
    @Autowired
    private ProfissionalRepository profissionalRepository;
    
    @Autowired
    private MatchResultRepository matchResultRepository;

    @Autowired
    private ProfissionalConfiguracaoRepository configRepository;

    public String abrirChamado(ChamadoRequest dto) {
        if (dto.getEmpresaId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Empresa não informada.");
        }
        if (dto.getEspecialidadeId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Especialidade não informada.");
        }
        if (dto.getDescricao() == null || dto.getDescricao().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Descrição não preenchida.");
        }
        if (dto.getUrgencia() == null || dto.getUrgencia().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Urgência não informada.");
        }
        if (dto.getOrcamentoMaximo() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Orçamento máximo não informado.");
        }
        if (dto.getCidade() == null || dto.getCidade().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cidade não informada.");
        }
        if (dto.getEstado() == null || dto.getEstado().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Estado não informado.");
        }

        Empresa empresa = empresaRepository.findById(dto.getEmpresaId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Empresa não encontrada."));

        Especialidade especialidade = especialidadeRepository.findById(dto.getEspecialidadeId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Especialidade não encontrada."));

        Chamado chamado = new Chamado();
        chamado.setEmpresa(empresa);
        chamado.setEspecialidade(especialidade);
        chamado.setDescricao(dto.getDescricao());
        chamado.setUrgencia(dto.getUrgencia());
        chamado.setOrcamentoMaximo(dto.getOrcamentoMaximo());
        chamado.setCidade(dto.getCidade());
        chamado.setEstado(dto.getEstado());
        chamado.setStatus("aberto");
        chamado.setAbertoEm(LocalDateTime.now());

        repository.save(chamado);

        return "Chamado aberto com sucesso.";
    }
    
    public List<ChamadoResponse> listarAbertos() {
        return repository.findByStatus("aberto").stream()
            .map(c -> {
                ChamadoResponse dto = new ChamadoResponse();
                dto.setId(c.getId());
                dto.setDescricao(c.getDescricao());
                dto.setUrgencia(c.getUrgencia());
                dto.setStatus(c.getStatus());
                dto.setOrcamentoMaximo(c.getOrcamentoMaximo());
                dto.setCidade(c.getCidade());
                dto.setEstado(c.getEstado());
                dto.setEspecialidadeNome(c.getEspecialidade().getNome());
                dto.setEmpresaNome(c.getEmpresa().getNome());
                return dto;
            })
            .collect(Collectors.toList());
    }

            public String candidatar(Long chamadoId, Long profissionalId) {
        Chamado chamado = repository.findById(chamadoId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Chamado não encontrado."));

        if (!"aberto".equals(chamado.getStatus())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Este chamado não está mais disponível.");
        }

        Profissional profissional = profissionalRepository.findById(profissionalId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Profissional não encontrado."));

        ProfissionalConfiguracao config = configRepository.findByProfissional(profissional)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Profissional sem configuração de score."));

        if (!config.getDisponivel()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Profissional indisponível no momento.");
        }

        MatchResult match = new MatchResult();
        match.setChamado(chamado);
        match.setProfissional(profissional);
        match.setScoreTotal(
    config.getScore() != null ? config.getScore().doubleValue() : null
);
        match.setStatusResposta("pendente");
        matchResultRepository.save(match);

        return "Candidatura registrada com sucesso.";
    }

    public List<MatchResultResponse> listarCandidatos(Long chamadoId) {
        return matchResultRepository.findByChamadoIdOrderByScoreTotalDesc(chamadoId).stream()
            .map(m -> {
                MatchResultResponse dto = new MatchResultResponse();
                dto.setId(m.getId());
                dto.setProfissionalId(m.getProfissional().getId());
                dto.setProfissionalNome(m.getProfissional().getNome());
                dto.setValorHora(m.getProfissional().getValorHora().doubleValue());
                dto.setScoreTotal(m.getScoreTotal().doubleValue());
                dto.setStatusResposta(m.getStatusResposta());
                return dto;
            })
            .collect(Collectors.toList());
    }

    public String aceitarCandidato(Long chamadoId, Long matchResultId) {
        Chamado chamado = repository.findById(chamadoId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Chamado não encontrado."));

        MatchResult escolhido = matchResultRepository.findById(matchResultId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Candidatura não encontrada."));

        chamado.setStatus("aceito");
        chamado.setProfissional(escolhido.getProfissional());
        repository.save(chamado);

        escolhido.setStatusResposta("aceito");
        matchResultRepository.save(escolhido);

        return "Candidato aceito com sucesso.";
    }

    public String recusarCandidato(Long matchResultId) {
        MatchResult recusado = matchResultRepository.findById(matchResultId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Candidatura não encontrada."));

        recusado.setStatusResposta("recusado");
        matchResultRepository.save(recusado);

        return "Candidato recusado.";
    }
    
    public List<ChamadoResponse> listarPorEmpresa(Long empresaId) {
    return repository.findByEmpresaId(empresaId).stream()
        .map(c -> {
            ChamadoResponse dto = new ChamadoResponse();
            dto.setId(c.getId());
            dto.setDescricao(c.getDescricao());
            dto.setUrgencia(c.getUrgencia());
            dto.setStatus(c.getStatus());
            dto.setOrcamentoMaximo(c.getOrcamentoMaximo());
            dto.setCidade(c.getCidade());
            dto.setEstado(c.getEstado());
            dto.setEspecialidadeNome(c.getEspecialidade().getNome());
            dto.setEmpresaNome(c.getEmpresa().getNome());
            return dto;
        })
        .collect(Collectors.toList());
}
}
