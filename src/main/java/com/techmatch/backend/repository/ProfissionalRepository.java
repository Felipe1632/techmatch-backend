/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.techmatch.backend.repository;

import com.techmatch.backend.model.EspecialidadeProfissionalDTO;
import com.techmatch.backend.model.ProfissionalConfiguracaoDTO;
import com.techmatch.backend.model.ProfissionalDTO;
import com.techmatch.backend.model.ProfissionalRequest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Usuario
 */
@Repository
public class ProfissionalRepository {

    private ProfissionalConfiguracaoRepository configuracaoRepository = new ProfissionalConfiguracaoRepository();
    private EspecialidadeRepository especialidadeRepository = new EspecialidadeRepository();

    public String register(ProfissionalRequest user) {
        String sql = "insert into profissional (nome, email, senha, cpf, telefone, cidade, estado, valor_hora) values (?,?,?,?,?,?,?,?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, user.getNome());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getSenha());
            stmt.setString(4, user.getCpf());
            stmt.setString(5, user.getTelefone());
            stmt.setString(6, user.getCidade());
            stmt.setString(7, user.getEstado());
            stmt.setDouble(8, user.getValor_hora());

            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas == 0) {
                throw new SQLException("Nenhuma linha foi alterada.");
            }

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                Long profissionalId = rs.getLong(1);
                ProfissionalConfiguracaoDTO config = new ProfissionalConfiguracaoDTO();
                config.setId_profissional(profissionalId);
                config.setRaio_atendimento_km(user.getRaioAtendimentoKm());
                configuracaoRepository.salvarConfiguracao(profissionalId, config);
                especialidadeRepository.salvarEspecialidades(profissionalId, user.getEspecialidades());
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return "Erro ao cadastrar profissional.";
        }

        return "Cadastro do profissional feito com sucesso.";
    }

    public ProfissionalDTO logar(String email, String senha) {
        String sql = "select * from profissional where email = ? and senha = ?";
        ProfissionalDTO user = new ProfissionalDTO();

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            stmt.setString(2, senha);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                user.setId(rs.getLong("id"));
                user.setNome(rs.getString("nome")); // estava "email" — corrigido
                user.setCpf(rs.getString("cpf"));
                user.setTelefone(rs.getString("telefone"));
                user.setCidade(rs.getString("cidade"));
                user.setEstado(rs.getString("estado"));
                user.setValor_hora(rs.getDouble("valor_hora"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }
    
    private ProfissionalDTO toProfissionalDTO(ProfissionalRequest p){
        ProfissionalDTO profissional = new ProfissionalDTO(
            p.nome,
            p.email,
            p.senha,
            p.cpf,
            p.telefone,
            p.cidade,
            p.estado,
            p.valor_hora
        );
        
        return profissional;
    }
}

