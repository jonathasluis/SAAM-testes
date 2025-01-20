/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.saam.teste.pratico.controller;

import com.mycompany.saam.teste.pratico.dao.UsuarioDao;
import com.mycompany.saam.teste.pratico.model.Usuario;
import com.mycompany.saam.teste.pratico.util.CriptografaSenha;
import com.mycompany.saam.teste.pratico.util.Secao;

/**
 * @author jonat
 */
public class LoginController {
    private UsuarioDao usuarioDao;

    public LoginController() {
        this.usuarioDao = new UsuarioDao();
    }

    public boolean autenticar(String email, String senha) {
        if (validaCampos(email, senha)) {
            Usuario user = usuarioDao.selectByEmail(email);
            if (user != null && CriptografaSenha.autenticarSenha(senha, user.getSenha())) {
                Secao.getInstancia().setUsuarioLogado(user);
                return true;
            } else {
                throw new IllegalArgumentException("Email ou senha inválidos");
            }
        }
        return false;
    }

    public boolean validaCampos(String email, String senha) {
        if (!email.trim().isEmpty() && !senha.trim().isEmpty()) {
            return true;
        } else {
            throw new IllegalArgumentException("Todos os campos são obrigatórios");
        }
    }
}
