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
import com.techmatch.backend.dto.MinhaCandidatura;
import com.techmatch.backend.model.Chamado;
import com.techmatch.backend.dto.ChamadoResponse;
import com.techmatch.backend.model.Avaliacao;
import com.techmatch.backend.model.Empresa;
import com.techmatch.backend.model.Especialidade;
import com.techmatch.backend.model.EspecialidadeProfissional;
import com.techmatch.backend.model.MatchResult;
import com.techmatch.backend.model.Profissional;
import com.techmatch.backend.model.ProfissionalConfiguracao;
import com.techmatch.backend.repository.AvaliacaoRepository;
import com.techmatch.backend.repository.ChamadoRepository;
import com.techmatch.backend.repository.EmpresaRepository;
import com.techmatch.backend.repository.EspecialidadeProfissionalRepository;
import com.techmatch.backend.repository.EspecialidadeRepository;
import com.techmatch.backend.repository.MatchResultRepository;
import com.techmatch.backend.repository.ProfissionalConfiguracaoRepository;
import com.techmatch.backend.repository.ProfissionalRepository;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
    
    @Autowired
    private EspecialidadeProfissionalRepository espProfRepository;

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

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
        List<Chamado> chamados = repository.findByStatus("aberto");
        List<ChamadoResponse> resultado = new ArrayList<>();

        for (Chamado c : chamados) {
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
            resultado.add(dto);
        }

        return resultado;
    }

            public String candidatar(Long chamadoId, Long profissionalId) {
    Chamado chamado = repository.findById(chamadoId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Chamado não encontrado."));

    if (!"aberto".equals(chamado.getStatus())) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Este chamado não está mais disponível.");
    }

    Profissional profissional = profissionalRepository.findById(profissionalId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Profissional não encontrado."));

    // NOVO: bloqueia candidatura repetida (inclusive após recusa)
    boolean jaCandidatou = !matchResultRepository.findByChamadoIdAndProfissionalId(chamadoId, profissionalId).isEmpty();
    if (jaCandidatou) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Você já se candidatou a este chamado.");
    }

    ProfissionalConfiguracao config = configRepository.findByProfissional(profissional)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Profissional sem configuração de score."));

    if (!config.getDisponivel()) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Profissional indisponível no momento.");
    }

    Double scoreCalculado = calcularScore(profissional, chamado, config);

    MatchResult match = new MatchResult();
    match.setChamado(chamado);
    match.setProfissional(profissional);
    match.setScoreTotal(scoreCalculado);
    match.setStatusResposta("pendente");
    matchResultRepository.save(match);

    return "Candidatura registrada com sucesso. Score: " + scoreCalculado;
}

        public List<MatchResultResponse> listarCandidatos(Long chamadoId) {
        List<MatchResult> matches = matchResultRepository.findByChamadoIdOrderByScoreTotalDesc(chamadoId);
        List<MatchResultResponse> resultado = new ArrayList<>();

        for (MatchResult m : matches) {
            MatchResultResponse dto = new MatchResultResponse();
            dto.setId(m.getId());
            dto.setProfissionalId(m.getProfissional().getId());
            dto.setProfissionalNome(m.getProfissional().getNome());
            dto.setValorHora(m.getProfissional().getValorHora().doubleValue());
            dto.setScoreTotal(m.getScoreTotal());
            dto.setStatusResposta(m.getStatusResposta());
            resultado.add(dto);
        }

        return resultado;
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
    List<Chamado> chamados = repository.findByEmpresaId(empresaId);
    List<ChamadoResponse> resultado = new ArrayList<>();

    for (Chamado c : chamados) {
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
        resultado.add(dto);
        }
        return resultado;
    }
    
    public List<MinhaCandidatura> listarMinhasCandidaturas(Long profissionalId) {
    List<MatchResult> matches = matchResultRepository.findByProfissionalId(profissionalId);
    List<MinhaCandidatura> resultado = new ArrayList<>();

    for (MatchResult m : matches) {
        MinhaCandidatura dto = new MinhaCandidatura();
        dto.setChamadoId(m.getChamado().getId());
        dto.setDescricao(m.getChamado().getDescricao());
        dto.setEmpresaNome(m.getChamado().getEmpresa().getNome());
        dto.setEspecialidadeNome(m.getChamado().getEspecialidade().getNome());
        dto.setStatusChamado(m.getChamado().getStatus());
        dto.setStatusResposta(m.getStatusResposta());
        dto.setScoreTotal(m.getScoreTotal());
        resultado.add(dto);
    }

    return resultado;
    }
    
    private Double calcularScore(Profissional profissional, Chamado chamado, ProfissionalConfiguracao config) {

    // 1. Skills (35%): nivel cadastrado na especialidade do chamado
    Double skills = 0.0;
    Optional<EspecialidadeProfissional> vinculo =
            espProfRepository.findByProfissionalAndEspecialidade(profissional, chamado.getEspecialidade());
    if (vinculo.isPresent()) {
        String nivel = vinculo.get().getNivel();
        if ("avancado".equals(nivel)) {
            skills = 100.0;
        } else if ("intermediario".equals(nivel)) {
            skills = 70.0;
        } else if ("basico".equals(nivel)) {
            skills = 40.0;
        }
    }

    // 2. Orçamento (25%): quanto mais dentro do orçamento máximo, melhor
    Double valorHora = profissional.getValorHora().doubleValue();
    Double orcamentoMaximo = chamado.getOrcamentoMaximo();
    Double orcamento;
    if (valorHora <= orcamentoMaximo) {
        orcamento = 100.0;
    } else {
        orcamento = Math.max(0.0, (orcamentoMaximo / valorHora) * 100.0);
    }

    // 3. Histórico (20%): média de avaliações anteriores (neutro = 70 sem histórico)
    Double media = avaliacaoRepository.mediaNotaPorProfissional(profissional.getId());
    Double historico = (media != null) ? (media / 5.0) * 100.0 : 70.0;

    // 4. Reputação (10%): campo score de profissional_configuracao
    Double reputacao = config.getScore().doubleValue();

    // 5. Disponibilidade (10%): já validado acima
    Double disponibilidade = config.getDisponivel() ? 100.0 : 0.0;

    Double total = (skills * 0.35) + (orcamento * 0.25) + (historico * 0.20) + (reputacao * 0.10) + (disponibilidade * 0.10);

    return Math.round(total * 100.0) / 100.0;
    }
    
   public List<ChamadoResponse> listarAbertosParaProfissional(Long profissionalId) {
    Profissional profissional = profissionalRepository.findById(profissionalId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Profissional não encontrado."));

    List<Chamado> chamados = repository.findByStatus("aberto");
    List<ChamadoResponse> resultado = new ArrayList<>();

    for (Chamado c : chamados) {
        boolean mesmaLocalizacao = c.getCidade().equalsIgnoreCase(profissional.getCidade())
                && c.getEstado().equalsIgnoreCase(profissional.getEstado());

        boolean jaCandidatou = !matchResultRepository.findByChamadoIdAndProfissionalId(c.getId(), profissionalId).isEmpty();

        if (mesmaLocalizacao && !jaCandidatou) {
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
            resultado.add(dto);
        }
    }

    return resultado;
}
    
    public String concluirChamado(Long chamadoId) {
    Chamado chamado = repository.findById(chamadoId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Chamado não encontrado."));

    if (!"aceito".equals(chamado.getStatus())) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Só é possível concluir um chamado que esteja aceito.");
    }

    chamado.setStatus("concluido");
    repository.save(chamado);

    return "Chamado concluído com sucesso.";
}

    public String avaliarProfissional(Long chamadoId, Integer nota) {
    Chamado chamado = repository.findById(chamadoId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Chamado não encontrado."));

    if (!"concluido".equals(chamado.getStatus())) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Só é possível avaliar um chamado concluído.");
    }

    if (chamado.getProfissional() == null) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Este chamado não tem profissional vinculado.");
    }

    if (nota == null || nota < 1 || nota > 5) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nota deve ser entre 1 e 5.");
    }

    Avaliacao avaliacao = new Avaliacao();
    avaliacao.setChamado(chamado);
    avaliacao.setProfissional(chamado.getProfissional());
    avaliacao.setNota(nota);
    avaliacaoRepository.save(avaliacao);

    return "Avaliação registrada com sucesso.";
}
    public String cancelarChamado(Long chamadoId) {
    Chamado chamado = repository.findById(chamadoId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Chamado não encontrado."));

    if (!"aberto".equals(chamado.getStatus())) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Só é possível cancelar um chamado que ainda esteja aberto.");
    }

    chamado.setStatus("cancelado");
    repository.save(chamado);

    return "Chamado cancelado com sucesso.";
}
}
