/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.techmatch.backend.repository;

import com.techmatch.backend.model.ProfissionalConfiguracaoDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Aluno
 */
@Repository
public class ProfissionalConfiguracaoRepository {
    
public void salvarConfiguracao(Long profissionalId, ProfissionalConfiguracaoDTO config) {
    String sql = "insert into profissional_configuracao (profissional_id, raio_atendimento_km) values (?, ?)";
    
    try (Connection conn = Conexao.conectar();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        
        stmt.setLong(1, profissionalId);
        stmt.setInt(2, config.raio_atendimento_km);
        stmt.executeUpdate();
        
    } catch (SQLException e) {
        e.printStackTrace();
    }
    }
}
