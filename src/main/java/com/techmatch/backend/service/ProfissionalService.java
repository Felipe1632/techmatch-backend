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
import com.techmatch.backend.service.TokenService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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
            config.setRaioAtendimentoKm(userRequest.getRaioAtendimentoKm());

            configRepository.save(config);

            return "Cadastro do profissional feito com sucesso.";
            
        }
                public String salvarEspecialidades(List<EspecialidadeProfissionalDTO> especialidades) {
        for (EspecialidadeProfissionalDTO dto : especialidades) {
            Profissional profissional = repository.findById(dto.getProfissional_id())
                .orElseThrow(() -> new ResponseStatusException(HttpStatusCode.valueOf(404), "Profissional não encontrado"));

            Especialidade esp = especialidadeRepository.findById(dto.getEspecialidade_id())
                .orElseThrow(() -> new ResponseStatusException(HttpStatusCode.valueOf(400), "Especialidade não encontrada"));

            EspecialidadeProfissional ep = new EspecialidadeProfissional();
            ep.setProfissional(profissional);
            ep.setEspecialidade(esp);
            ep.setNivel(dto.getNivel());
            ep.setAnos_experiencia(dto.getAnos_experiencia());
            espProfRepository.save(ep);
    }
                return "Especialidades salvas com sucesso.";
                }
     
        public String logar(UserRequestDTO user){
        String mensagem = "";
        if(user.getEmail().equals("")){
            mensagem = "Email não preenchido";
        } else if(user.getSenha().equals("")){
            mensagem = "Senha não preenchida";
        }
        
        if(!mensagem.equals("")){
            throw new ResponseStatusException(HttpStatusCode.valueOf(400), mensagem);
        }
        Profissional profissional = repository.findByEmailAndSenha(user.getEmail(), user.getSenha());
            return tokenservice.gerarToken(profissional.getId(),user.getEmail(), user.getSenha());
        }          
}
