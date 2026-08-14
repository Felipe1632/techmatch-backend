/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.techmatch.backend.repository;

import com.techmatch.backend.model.Chamado;
import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Aluno
 */
@Repository
public interface ChamadoRepository extends JpaRepository<Chamado, Long>{
    
    List<Chamado> findByStatus(String status);
    List<Chamado> findByEmpresaId(Long empresaId);
}
