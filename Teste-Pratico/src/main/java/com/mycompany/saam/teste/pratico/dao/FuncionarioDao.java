/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.saam.teste.pratico.dao;

import com.mycompany.saam.teste.pratico.model.Funcionario;
import com.mycompany.saam.teste.pratico.util.PostgresDBConnect;
import com.mycompany.saam.teste.pratico.util.Secao;
import com.mycompany.saam.teste.pratico.util.SqlGeneration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author jonat
 */
public class FuncionarioDao implements Dao<Funcionario> {

    @Override
    public Boolean insert(Funcionario funcionario) {
        String sql = SqlGeneration.insertSql(funcionario);
        try (Connection conn = PostgresDBConnect.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, funcionario.getNome());
            pstmt.setDate(2, funcionario.getDataAdmissao());
            pstmt.setDouble(3, funcionario.getValorSalario());
            pstmt.setBoolean(4, funcionario.getStatus());
            pstmt.setObject(5, UUID.fromString(funcionario.getIdUsuario()));
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Erro ao adicionar funcionario: " + e.getMessage());
            return false;
        }
    }

    @Override
    public List<Funcionario> select() {
        ArrayList<Funcionario> funcionarios = new ArrayList<>();
        String sql = SqlGeneration.selectSql(new Funcionario(), " WHERE usuarioID = ?");
        try (Connection conn = PostgresDBConnect.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setObject(1, UUID.fromString(Secao.getInstancia().getUsuarioLogado().getId()));
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Funcionario funcionario = new Funcionario();
                funcionario.setId(rs.getString("id"));
                funcionario.setNome(rs.getString("nome"));
                funcionario.setDataAdmissao(rs.getDate("dataadmissao"));
                funcionario.setValorSalario(rs.getDouble("valorsalario"));
                funcionario.setStatus(rs.getBoolean("status"));
                funcionario.setIdUsuario(rs.getString("usuarioID"));
                funcionarios.add(funcionario);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar funcionario: " + e.getMessage());
        }
        return funcionarios;
    }

    @Override
    public Funcionario selectById(String id) {
        Funcionario funcionario = new Funcionario();
        String sql = SqlGeneration.selectSql(funcionario, "WHERE id = ?");
        try (Connection conn = PostgresDBConnect.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setObject(1, UUID.fromString(id));
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                funcionario.setId(rs.getString("id"));
                funcionario.setNome(rs.getString("nome"));
                funcionario.setDataAdmissao(rs.getDate("dataadmissao"));
                funcionario.setValorSalario(rs.getDouble("valorsalario"));
                funcionario.setStatus(rs.getBoolean("status"));
                funcionario.setIdUsuario(rs.getString("usuarioID"));
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar funcionario: " + e.getMessage());
        }
        return funcionario;
    }

    @Override
    public Boolean update(Funcionario model, String id) {
        String sql = SqlGeneration.updateSql(model);
        try (Connection conn = PostgresDBConnect.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, model.getNome());
            pstmt.setDate(2, model.getDataAdmissao());
            pstmt.setDouble(3, model.getValorSalario());
            pstmt.setBoolean(4, model.getStatus());
            pstmt.setObject(5, UUID.fromString(model.getIdUsuario()));
            pstmt.setObject(6, UUID.fromString(id));
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar funcionario: " + e.getMessage());
            return false;
        }
    }

    @Override
    public Boolean delete(String id) {
        String sql = SqlGeneration.deleteSql(new Funcionario());
        try (Connection conn = PostgresDBConnect.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setObject(1, UUID.fromString(id));
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Erro ao deletar funcionario: " + e.getMessage());
            return false;
        }
    }
}
