/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.techmatch.backend.repository;

import com.techmatch.backend.model.ProfissionalDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Usuario
 */
@Repository
public class ProfissionalRepository {
        public void register(ProfissionalDTO user){
      try{
         Connection conn = Conexao.conectar();
         PreparedStatement stmt = null;
         stmt = conn.prepareStatement("insert into profissional (nome, email, senha, cpf, telefone, cidade, estado) values (?,?,?,?,?,?,?)");
         
         stmt.setString(1, user.getNome());
         stmt.setString(2, user.getEmail());
         stmt.setString(3, user.getSenha());
         stmt.setString(4, user.getCpf());
         stmt.setString(5, user.getTelefone());
         stmt.setString(6, user.getCidade());
         stmt.setString(7, user.getEstado());
         
         int linhasAfetadas = stmt.executeUpdate();
         if(linhasAfetadas == 0){
             throw new SQLException("Falha na atualização - Nenhuma linha foi alterada.");
         }
      }catch(SQLException e){
          e.printStackTrace();
      }    
   }
    
    public ProfissionalDTO logar(String email, String senha){
        ProfissionalDTO user = new ProfissionalDTO();
        
        try{
            Connection conn = Conexao.conectar();
            PreparedStatement stmt = null;
            ResultSet rs = null;
            
            stmt = conn.prepareStatement("select * from profissional where email = ? && senha = ?");
            
            stmt.setString(1, email);
            stmt.setString(2, senha);
            
            rs = stmt.executeQuery();
            
            if(rs.next()){
                user.setId(rs.getLong("id"));
                user.setNome(rs.getString("email"));
                user.setEmail(rs.getString("senha"));
                user.setCpf(rs.getString("cpf"));
                user.setTelefone(rs.getString("telefone"));
                user.setCidade(rs.getString("cidade"));
                user.setEstado(rs.getString("estado"));
                
            }
            
        }catch(SQLException e){
            e.printStackTrace();
        }
        
        return user;
    }
}
