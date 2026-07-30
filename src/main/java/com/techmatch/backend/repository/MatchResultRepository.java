/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.techmatch.backend.repository;

import com.techmatch.backend.model.MatchResult;
import org.springframework.stereotype.Controller;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Aluno
 */
@Controller
public interface MatchResultRepository extends JpaRepository<MatchResult, Long>{
    
}
