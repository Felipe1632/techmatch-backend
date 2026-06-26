/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.techmatch.backend.repository;

import com.techmatch.backend.model.EspecialidadeProfissionalDTO;
import java.sql.SQLException;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Aluno
 */
@Repository
public class EspecialidadeRepository {
    
    public void salvarEspecialidades(Long profissionalId, List<EspecialidadeProfissionalDTO> especialidades){
        try{
            
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
