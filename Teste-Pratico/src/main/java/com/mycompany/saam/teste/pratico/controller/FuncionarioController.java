/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.saam.teste.pratico.controller;

import com.mycompany.saam.teste.pratico.dao.FuncionarioDao;
import com.mycompany.saam.teste.pratico.model.Funcionario;
import com.mycompany.saam.teste.pratico.util.Secao;

import java.sql.Date;

/**
 * @author jonat
 */
public class FuncionarioController {

    private FuncionarioDao funcionarioDao;

    public FuncionarioController() {
        this.funcionarioDao = new FuncionarioDao();
    }

    public boolean cadastrar(String nome, String data, String salario, boolean status) throws Exception {
        if (validaCampos(nome, data, salario)) {
            double salarioConvertido = Double.parseDouble(salario);
            Funcionario funcionario = new Funcionario(nome, Date.valueOf(data), salarioConvertido, status, Secao.getInstancia().getUsuarioLogado().getId());
            if (funcionarioDao.insert(funcionario)) {
                return true;
            } else {
                throw new Exception("Erro ao adicionar Funcionario");
            }
        }
        return false;
    }

    public boolean alterar(String nome, String data, String salario, boolean status, String id) throws Exception {
        if (validaCampos(nome, data, salario)) {
            double salarioConvertido = Double.parseDouble(salario);
            Funcionario funcionario = new Funcionario(nome, Date.valueOf(data), salarioConvertido, status, Secao.getInstancia().getUsuarioLogado().getId());
            if (funcionarioDao.update(funcionario, id)){
                return true;
            } else {
                throw new Exception("Erro ao alterar Funcionario");
            }
        }
        return false;
    }

    public boolean deletar(String id) throws Exception {
        if (funcionarioDao.delete(id)) {
            return true;
        } else {
            throw new Exception("Erro ao deletar Funcionario");
        }
    }

    private boolean validaCampos(String nome, String data, String salario) {
        if (nome.trim().isEmpty() || data.trim().isEmpty() || salario.trim().isEmpty() || Double.parseDouble(salario) <= 0) {
            throw new IllegalArgumentException("Todos os campos são obrigatórios");
        } else {
            return true;
        }
    }

    public Funcionario retornaFuncionarioSelecionado(String idFuncionario) {
        return funcionarioDao.selectById(idFuncionario);
    }

}
