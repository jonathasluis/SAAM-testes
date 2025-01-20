/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.curso.open.swing.dao;

import br.com.curso.open.swing.util.Conexao;
import br.com.curso.open.swing.util.Util;
import br.com.curso.open.swing.vo.FuncionarioVO;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.openswing.swing.message.receive.java.ErrorResponse;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.VOListResponse;
import org.openswing.swing.message.receive.java.VOResponse;
import org.openswing.swing.message.send.java.GridParams;
import org.openswing.swing.server.QueryUtil;

/**
 *
 * @author jonat
 */
public class FuncionarioDao {
    
    public Response loadData(GridParams gridParams) {
        
        try {
            Connection conn = Conexao.getConexao();
            
            String sql = "SELECT\n"
                    + "      id,\n"
                    + "      nome,\n"
                    + "      sexo,\n"
                    + "      salario,\n"
                    + "      data_de_entrada,\n"
                    + "      data_de_saida,\n"
                    + "      cidade,\n"
                    + "      logradouro,\n"
                    + "      numero,\n"
                    + "      complemento,\n"
                    + "      bairro,\n"
                    + "      uf,\n"
                    + "      cep,\n"
                    + "      fk_cargo,\n"
                    + "      cargo\n"
                    + "from (SELECT \n"
                    + "  a.id,\n"
                    + "  nome,\n"
                    + "  sexo,\n"
                    + "  salario,\n"
                    + "  data_de_entrada,\n"
                    + "  data_de_saida,\n"
                    + "  cidade,\n"
                    + "  logradouro,\n"
                    + "  numero,\n"
                    + "  complemento,\n"
                    + "  bairro,\n"
                    + "  uf,\n"
                    + "  masc_cep(cep) as cep,\n"
                    + "  fk_cargo,\n"
                    + "  cargo\n"
                    + "FROM \n"
                    + "public.funcionario a\n"
                    + "inner join cargo b\n"
                    + "on a.fk_cargo = b.id) a";
            
            Map attribute2dbField = getDBMapping();
            
            return QueryUtil.getQuery(
                    conn,
                    sql,
                    new ArrayList(),
                    attribute2dbField,
                    FuncionarioVO.class,
                    "t",
                    "f",
                    gridParams,
                    false
            );
            
        } catch (Exception ex) {
            
            Util.getAlert().alertErro("Erro ao carregar os dados:" + ex.getMessage());
            return new ErrorResponse(ex.getMessage());
        }
    }
    
    private Map getDBMapping() {
        Map attribute2dbField = new HashMap();
        attribute2dbField.put("id", "id");
        attribute2dbField.put("nome", "nome");
        attribute2dbField.put("sexo", "sexo");
        attribute2dbField.put("salario", "salario");
        attribute2dbField.put("data_de_entrada", "data_de_entrada");
        attribute2dbField.put("data_de_saida", "data_de_saida");
        attribute2dbField.put("cidade", "cidade");
        attribute2dbField.put("logradouro", "logradouro");
        attribute2dbField.put("numero", "numero");
        attribute2dbField.put("complemento", "complemento");
        attribute2dbField.put("bairro", "bairro");
        attribute2dbField.put("uf", "uf");
        attribute2dbField.put("cep", "cep");
        attribute2dbField.put("fk_cargo", "fk_cargo");
        attribute2dbField.put("cargo", "cargo");
        
        return attribute2dbField;
    }
    
    public Response insertRecords(ArrayList<FuncionarioVO> newValueObjects) {
        PreparedStatement ps = null;
        String sql = "INSERT INTO \n"
                + "  public.funcionario\n"
                + "(\n"
                + "  nome,\n"
                + "  sexo,\n"
                + "  salario,\n"
                + "  data_de_entrada,\n"
                + "  data_de_saida,\n"
                + "  cidade,\n"
                + "  logradouro,\n"
                + "  numero,\n"
                + "  complemento,\n"
                + "  bairro,\n"
                + "  uf,\n"
                + "  cep,\n"
                + "  fk_cargo\n"
                + ")\n"
                + "VALUES (\n"
                + "  ?,\n"
                + "  ?,\n"
                + "  ?,\n"
                + "  ?,\n"
                + "  ?,\n"
                + "  ?,\n"
                + "  ?,\n"
                + "  ?,\n"
                + "  ?,\n"
                + "  ?,\n"
                + "  ?,\n"
                + "  ?,\n"
                + "  ?\n"
                + ");";
        try {
            for (FuncionarioVO vo : newValueObjects) {
                ps = Conexao.getConexao().prepareStatement(sql);
                ps.setString(1, vo.getNome() == null ? "" : vo.getNome().trim());
                ps.setString(2, vo.getSexo() == null ? "" : vo.getSexo().trim());
                ps.setBigDecimal(3, vo.getSalario() == null ? BigDecimal.ZERO : vo.getSalario());
                ps.setDate(4, new Date(vo.getData_de_entrada().getTime()));
                ps.setDate(5, vo.getData_de_saida() == null ? null : new Date(vo.getData_de_saida().getTime()));
                ps.setString(6, vo.getCidade() == null ? "" : vo.getCidade().trim());
                ps.setString(7, vo.getLogradouro() == null ? "" : vo.getLogradouro().trim());
                ps.setString(8, vo.getNumero() == null ? "" : vo.getNumero().trim());
                ps.setString(9, vo.getComplemento() == null ? "" : vo.getComplemento().trim());
                ps.setString(10, vo.getBairro() == null ? "" : vo.getBairro().trim());
                ps.setString(11, vo.getUf() == null ? "" : vo.getUf().trim());
                ps.setString(12, vo.getCep() == null ? "" : vo.getCep().replaceAll("[.-]", "").trim());
                ps.setInt(13, 2);
                ps.executeUpdate();
            }
        } catch (SQLException ex) {
            Util.getAlert().alertErro("Erro ao adicionar o funcionario:" + ex.getMessage());
        } finally {
            closePs(ps);
        }
        return new VOListResponse(newValueObjects, false, newValueObjects.size());
    }
    
    public Response updateRecords(ArrayList<FuncionarioVO> persistentObjects) {
        PreparedStatement ps = null;
        String sql = "UPDATE \n"
                + "  public.funcionario \n"
                + "SET \n"
                + "  nome = ?,\n"
                + "  sexo = ?,\n"
                + "  salario = ?,\n"
                + "  data_de_entrada = ?,\n"
                + "  data_de_saida = ?,\n"
                + "  cidade = ?,\n"
                + "  logradouro = ?,\n"
                + "  numero = ?,\n"
                + "  complemento = ?,\n"
                + "  bairro = ?,\n"
                + "  uf = ?,\n"
                + "  cep = ?,\n"
                + "  fk_cargo = ?\n"
                + "WHERE \n"
                + "id = ?;";
        
        try {
            
            for (FuncionarioVO vo : persistentObjects) {
                ps = Conexao.getConexao().prepareStatement(sql);
                ps.setString(1, vo.getNome() == null ? "" : vo.getNome().trim());
                ps.setString(2, vo.getSexo() == null ? "" : vo.getSexo().trim());
                ps.setBigDecimal(3, vo.getSalario() == null ? BigDecimal.ZERO : vo.getSalario());
                ps.setDate(4, new Date(vo.getData_de_entrada().getTime()));
                ps.setDate(5, vo.getData_de_saida() == null ? null : new Date(vo.getData_de_saida().getTime()));
                ps.setString(6, vo.getCidade() == null ? "" : vo.getCidade().trim());
                ps.setString(7, vo.getLogradouro() == null ? "" : vo.getLogradouro().trim());
                ps.setString(8, vo.getNumero() == null ? "" : vo.getNumero().trim());
                ps.setString(9, vo.getComplemento() == null ? "" : vo.getComplemento().trim());
                ps.setString(10, vo.getBairro() == null ? "" : vo.getBairro().trim());
                ps.setString(11, vo.getUf() == null ? "" : vo.getUf().trim());
                ps.setString(12, vo.getCep() == null ? "" : vo.getCep().replaceAll("[.-]", "").trim());
                ps.setInt(13, 2);
                ps.setInt(14, vo.getId());
                ps.executeUpdate();
            }
        } catch (SQLException ex) {
            Util.getAlert().alertErro("Erro ao alterar o funcionário" + ex.getMessage());
        } finally {
            closePs(ps);
        }
        return new VOListResponse(persistentObjects, false, persistentObjects.size());
    }
    
    public Response deleteRecords(ArrayList<FuncionarioVO> persistentObjects) {
        PreparedStatement ps = null;
        try {
            for (FuncionarioVO vo : persistentObjects) {
                ps = Conexao.getConexao().prepareStatement("DELETE FROM public.funcionario WHERE id = " + vo.getId() + ";");
                ps.executeUpdate();
            }
        } catch (SQLException ex) {
            Util.getAlert().alertErro("Erro ao excluir o funcionário" + ex.getMessage());
        } finally {
            closePs(ps);
        }
        return new VOResponse(true);
    }
    
    private void closePs(PreparedStatement ps) {
        try {
            if (ps != null) {
                ps.close();
            }
        } catch (SQLException ex) {
            Util.getAlert().alertErro("Erro ao fechar o ps:" + ex.getMessage());
        }
    }
}
