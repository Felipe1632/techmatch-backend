/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.techmatch.backend.service;

import com.techmatch.backend.dto.EspecialidadeProfissionalDTO;
import com.techmatch.backend.model.EspecialidadeProfissional;
import com.techmatch.backend.model.Profissional;
import com.techmatch.backend.model.ProfissionalConfiguracao;
import com.techmatch.backend.dto.ProfissionalRequest;
import com.techmatch.backend.dto.UserRequestDTO;
import com.techmatch.backend.model.Especialidade;
import com.techmatch.backend.repository.EspecialidadeProfissionalRepository;
import com.techmatch.backend.repository.EspecialidadeRepository;
import com.techmatch.backend.repository.ProfissionalConfiguracaoRepository;
import com.techmatch.backend.repository.ProfissionalRepository;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author Usuario
 */
@Service
public class ProfissionalService {
  
    @Autowired
    private ProfissionalRepository repository;
    
    @Autowired
    private ProfissionalConfiguracaoRepository configRepository;
    
    @Autowired
    private EspecialidadeRepository especialidadeRepository;

    @Autowired
    private EspecialidadeProfissionalRepository espProfRepository;
    
    @Autowired
    private TokenService tokenservice;
    
        public String register(ProfissionalRequest userRequest) {
            String mensagem = "";
            if (userRequest.getNome().equals("")) {
                mensagem = "Nome não preenchido";
            } else if (userRequest.getEmail().equals("")) {
                mensagem = "Email não preenchido";
            } else if (userRequest.getSenha().equals("")) {
                mensagem = "Senha não preenchida";
            } else if (userRequest.getCpf().equals("")) {
                mensagem = "CPF não preenchido";
            } else if (userRequest.getTelefone().equals("")) {
                mensagem = "Telefone não preenchido";
            } else if (userRequest.getCidade().equals("")) {
                mensagem = "Cidade não preenchida";
            } else if (userRequest.getEstado().equals("")) {
                mensagem = "Estado não preenchido";
            } //else if (userRequest.getEspecialidades() == null || userRequest.getEspecialidades().isEmpty()) {
                //mensagem = "Informe ao menos uma especialidade";
            //}

            if (!mensagem.equals("")) {
                throw new ResponseStatusException(HttpStatusCode.valueOf(400), mensagem);
            }

            Profissional p = new Profissional();
            p.setNome(userRequest.getNome());
            p.setEmail(userRequest.getEmail());
            p.setSenha(userRequest.getSenha());
            p.setCpf(userRequest.getCpf());
            p.setTelefone(userRequest.getTelefone());
            p.setCidade(userRequest.getCidade());
            p.setEstado(userRequest.getEstado());
            p.setValorHora(userRequest.getValorHora());
            p.setStatus("pendente");
            Profissional salvo = repository.save(p);

            ProfissionalConfiguracao config = new ProfissionalConfiguracao();
            config.setProfissional(salvo);
            Integer raio = (userRequest.getRaioAtendimentoKm() != null) 
                               ? userRequest.getRaioAtendimentoKm() 
                               : 10;
                config.setRaioAtendimentoKm(raio);

    configRepository.save(config);

            configRepository.save(config);

            return "Cadastro do profissional feito com sucesso.";
            
        }
        
        public String salvarEspecialidades(List<EspecialidadeProfissionalDTO> especialidades) {
           if (especialidades == null || especialidades.isEmpty()) {
               throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A lista de especialidades não pode estar vazia.");
           }

           for (EspecialidadeProfissionalDTO dto : especialidades) {
               if (dto.getProfissionalId() == null) {
                   throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O ID do profissional não foi informado.");
               }
               if (dto.getEspecialidadeId() == null) {
                   throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O ID da especialidade não foi informado.");
               }

               Profissional profissional = repository.findById(dto.getProfissionalId())
                       .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Profissional não encontrado com o ID: " + dto.getProfissionalId()));

               Especialidade especialidade = especialidadeRepository.findById(dto.getEspecialidadeId())
                       .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Especialidade não encontrada com o ID: " + dto.getEspecialidadeId()));

               EspecialidadeProfissional relacao = new EspecialidadeProfissional();
               relacao.setProfissional(profissional);
               relacao.setEspecialidade(especialidade);

               relacao.setNivel(dto.getNivel());
               relacao.setAnos_experiencia(dto.getAnosExperiencia() != null ? dto.getAnosExperiencia(): 0);

               espProfRepository.save(relacao);
           }

           return "Especialidades associadas com sucesso.";
       }
     
        public String logar(UserRequestDTO user) {
            String mensagem = "";
            if (user.getEmail().equals("")) {
                mensagem = "Email não preenchido";
            } else if (user.getSenha().equals("")) {
                mensagem = "Senha não preenchida";
            } if (!mensagem.equals("")) {
                throw new ResponseStatusException(HttpStatusCode.valueOf(400), mensagem);
            }

            Profissional profissional = repository.findByEmailAndSenha(user.getEmail(), user.getSenha());

            if (profissional == null) {
                throw new ResponseStatusException(HttpStatusCode.valueOf(401), "Credenciais inválidas");
            } if ("pendente".equals(profissional.getStatus())) {
                throw new ResponseStatusException(HttpStatusCode.valueOf(403), "Seu cadastro ainda está em análise pelo administrador.");
            } if ("suspenso".equals(profissional.getStatus())) {
                throw new ResponseStatusException(HttpStatusCode.valueOf(403), "Seu cadastro foi rejeitado ou suspenso.");
            }
            return tokenservice.gerarToken(profissional.getId(), user.getEmail(), user.getSenha());
        }
        
        public ProfissionalRequest buscarPorEmail(String email) {
            Profissional profissional = repository.findByEmail(email)
                    .orElseThrow(() -> new RuntimeException("Profissional não encontrado"));

            ProfissionalConfiguracao config = configRepository.findByProfissional(profissional)
                    .orElse(null);

            ProfissionalRequest dto = new ProfissionalRequest();
            dto.setId(profissional.getId());
            dto.setNome(profissional.getNome());
            dto.setEmail(profissional.getEmail());
            dto.setTelefone(profissional.getTelefone());
            dto.setValorHora(profissional.getValorHora());
            dto.setCidade(profissional.getCidade());
            dto.setEstado(profissional.getEstado());

            // Repassa direto sem precisar de conversões manuais:
            dto.setEspecialidades(espProfRepository.findByProfissional(profissional));

            if (config != null) {
                dto.setRaioAtendimentoKm(config.getRaioAtendimentoKm());
                dto.setRaioAtendimentoKm(config.getRaioAtendimentoKm());
                dto.setScore(config.getScore().doubleValue());
            }

            return dto;
        }
        
    @Transactional
    public void removerEspecialidade(Long profissionalId, Long especialidadeId) {       
        espProfRepository.removerPorProfissionalEEspecialidade(profissionalId, especialidadeId);
        
        }
        public String atualizar(Long id, ProfissionalRequest dto) {
        Profissional profissional = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Profissional não encontrado."));

        if (dto.getNome() != null && !dto.getNome().isEmpty()) {
            profissional.setNome(dto.getNome());
        }
        if (dto.getTelefone() != null && !dto.getTelefone().isEmpty()) {
            profissional.setTelefone(dto.getTelefone());
        }
        if (dto.getCidade() != null && !dto.getCidade().isEmpty()) {
            profissional.setCidade(dto.getCidade());
        }
        if (dto.getEstado() != null && !dto.getEstado().isEmpty()) {
            profissional.setEstado(dto.getEstado());
        }
        if (dto.getValorHora() != null) {
            profissional.setValorHora(dto.getValorHora());
        }

        repository.save(profissional);
        return "Perfil atualizado com sucesso.";
    }
        
public List<ProfissionalRequest> listarPendentes() {
    List<Profissional> pendentes = repository.findByStatus("pendente");
    List<ProfissionalRequest> resultado = new ArrayList<>();

    for (Profissional p : pendentes) {
        ProfissionalRequest dto = new ProfissionalRequest();
        dto.setId(p.getId());
        dto.setNome(p.getNome());
        dto.setEmail(p.getEmail());
        dto.setTelefone(p.getTelefone());
        dto.setCidade(p.getCidade());
        dto.setEstado(p.getEstado());
        dto.setValorHora(p.getValorHora());
        resultado.add(dto);
    }

    return resultado;
}

    public String alterarStatus(Long id, String novoStatus) {
    Profissional profissional = repository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Profissional não encontrado."));
    profissional.setStatus(novoStatus);
    repository.save(profissional);
    return "Status atualizado para: " + novoStatus;
    }
    
    public List<ProfissionalRequest> listarTodos() {
    List<Profissional> todos = repository.findAll();
    List<ProfissionalRequest> resultado = new ArrayList<>();

    for (Profissional p : todos) {
        ProfissionalRequest dto = new ProfissionalRequest();
        dto.setId(p.getId());
        dto.setNome(p.getNome());
        dto.setEmail(p.getEmail());
        dto.setTelefone(p.getTelefone());
        dto.setCidade(p.getCidade());
        dto.setEstado(p.getEstado());
        dto.setValorHora(p.getValorHora());
        dto.setStatus(p.getStatus());
        resultado.add(dto);
    }

    return resultado;
}
}
