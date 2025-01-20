/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.saam.teste.pratico.controller;

import com.mycompany.saam.teste.pratico.dao.FuncionarioDao;
import com.mycompany.saam.teste.pratico.model.Funcionario;

import javax.swing.table.DefaultTableModel;
import java.util.List;

/**
 * @author jonat
 */
public class FuncionarioBrwController {
    private FuncionarioDao funcionarioDao;

    public FuncionarioBrwController() {
        this.funcionarioDao = new FuncionarioDao();
    }

    public void preencheTableModel(DefaultTableModel tableModel) {
        List<Funcionario> list = funcionarioDao.select();
        for (Funcionario funcionario : list) {
            tableModel.addRow(new Object[]{
                    funcionario.getId(),
                    funcionario.getNome(),
                    funcionario.getDataAdmissao(),
                    funcionario.getValorSalario(),
                    funcionario.getStatus()
            });
        }
    }

}
