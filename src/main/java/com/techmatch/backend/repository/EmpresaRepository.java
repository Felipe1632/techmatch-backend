/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.techmatch.backend.repository;

import com.techmatch.backend.model.Empresa;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Aluno
 */
@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Long>{
    Empresa findByEmailAndSenha(String email, String senha);
    
    Optional<Empresa> findByEmail(String email);
    
    List<Empresa> findByStatus(String status);
}
