/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.techmatch.backend.repository;

import com.techmatch.backend.model.EspecialidadeProfissionalDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Aluno
 */
@Repository
public class EspecialidadeRepository {
    
    public void salvarEspecialidades(Long profissionalId, List<EspecialidadeProfissionalDTO> especialidades) {
        
        String sql = "insert into profissional_especialidade (profissional_id, especialidade_id, nivel, anos_experiencia) values (?, ?, ?, ?)";
        
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            for (EspecialidadeProfissionalDTO esp : especialidades) {
                stmt.setLong(1, profissionalId);
                stmt.setLong(2, esp.especialidade_id);
                stmt.setString(3, esp.nivel);
                stmt.setInt(4, esp.anos_experiencia);
                stmt.executeUpdate();
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}