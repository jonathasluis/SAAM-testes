/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.saam.teste.pratico.dao;

import com.mycompany.saam.teste.pratico.model.Usuario;
import com.mycompany.saam.teste.pratico.util.PostgresDBConnect;
import com.mycompany.saam.teste.pratico.util.SqlGeneration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author jonat
 */
public class UsuarioDao implements Dao<Usuario> {

    @Override
    public Boolean insert(Usuario usuario) {
        String sql = SqlGeneration.insertSql(usuario);
        try (Connection conn = PostgresDBConnect.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, usuario.getNome());
            pstmt.setString(2, usuario.getEmail());
            pstmt.setString(3, usuario.getSenha()); // A senha já deve estar criptografada
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Erro ao adicionar usuário: " + e.getMessage());
            return false;
        }
    }

    @Override
    public List<Usuario> select() {
        ArrayList<Usuario> usuarios = new ArrayList<>();
        String sql = SqlGeneration.selectSql(new Usuario());
        try (Connection conn = PostgresDBConnect.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(rs.getString("id"));
                usuario.setNome(rs.getString("nome"));
                usuario.setEmail(rs.getString("email"));
                usuario.setSenha(rs.getString("senha"));
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar usuários: " + e.getMessage());
        }
        return usuarios;

    }

    @Override
    public Usuario selectById(String id) {
        String sql = SqlGeneration.selectSql(new Usuario(), " WHERE id = ?::uuid ");
        return selectWithWhere(sql, id);
    }

    public Usuario selectByEmail(String email) {
        String sql = SqlGeneration.selectSql(new Usuario(), " WHERE email = ? ");
        return selectWithWhere(sql, email);
    }

    private Usuario selectWithWhere(String sql, String arg) {
        try (Connection conn = PostgresDBConnect.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, arg);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(rs.getString("id"));
                usuario.setNome(rs.getString("nome"));
                usuario.setEmail(rs.getString("email"));
                usuario.setSenha(rs.getString("senha"));
                return usuario;
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar usuários: " + e.getMessage());
        }
        return null;
    }

    @Override
    public Boolean update(Usuario model, String id) {
        String sql = SqlGeneration.updateSql(model);
        try (Connection conn = PostgresDBConnect.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, model.getNome());
            pstmt.setString(2, model.getEmail());
            pstmt.setString(3, model.getSenha());
            pstmt.setString(4, id);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar usuário: " + e.getMessage());
            return false;
        }
    }

    @Override
    public Boolean delete(String id) {
        String sql = SqlGeneration.deleteSql(new Usuario());
        try (Connection conn = PostgresDBConnect.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, id);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Erro ao deletar usuário: " + e.getMessage());
            return false;
        }
    }
}
