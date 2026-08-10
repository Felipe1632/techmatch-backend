/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.techmatch.backend.repository;

import com.techmatch.backend.model.EspecialidadeProfissional;
import com.techmatch.backend.model.Profissional;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Aluno
 */
@Repository
public interface EspecialidadeProfissionalRepository extends JpaRepository<EspecialidadeProfissional, Long>{
    
    List<EspecialidadeProfissional> findByProfissional(Profissional profissional);
}
