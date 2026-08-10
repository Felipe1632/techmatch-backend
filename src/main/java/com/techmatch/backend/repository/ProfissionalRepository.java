/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.techmatch.backend.repository;

import com.techmatch.backend.model.Profissional;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Usuario
 */
@Repository
public interface ProfissionalRepository extends JpaRepository<Profissional, Long>{

    Profissional findByEmailAndSenha(String email, String senha);

    // O Spring JPA gera automaticamente a query SQL "SELECT * FROM profissional WHERE email = ?"
    Optional<Profissional> findByEmail(String email);

}
