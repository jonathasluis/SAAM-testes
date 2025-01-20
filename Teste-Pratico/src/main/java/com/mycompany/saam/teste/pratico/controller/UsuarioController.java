package com.mycompany.saam.teste.pratico.controller;

import com.mycompany.saam.teste.pratico.dao.UsuarioDao;
import com.mycompany.saam.teste.pratico.model.Usuario;
import com.mycompany.saam.teste.pratico.util.CriptografaSenha;
import com.mycompany.saam.teste.pratico.util.Operacao;
import com.mycompany.saam.teste.pratico.util.Secao;
import com.mycompany.saam.teste.pratico.util.ValidaEmail;

public class UsuarioController {
    private UsuarioDao usuarioDao;

    public UsuarioController() {
        this.usuarioDao = new UsuarioDao();
    }

    public boolean cadastrar(String nome, String email, String senha) throws Exception {
        if (validaCampos(nome, email, senha, Operacao.INCLUIR)) {
            Usuario user = new Usuario(nome, email, CriptografaSenha.criptografarSenha(senha));
            if (usuarioDao.insert(user)){
                return true;
            } else {
                throw new Exception("Erro ao cadastrar usuario");
            }
        }
        return false;
    }

    public boolean alterar(String nome, String email, String senha) throws Exception {
        if (validaCampos(nome, email, senha, Operacao.ALTERAR)) {
            Usuario user = Secao.getInstancia().getUsuarioLogado();
            user.setNome(nome);
            user.setEmail(email);
            if (!senha.trim().isEmpty()) {
                user.setSenha(CriptografaSenha.criptografarSenha(senha));
            }
            if (usuarioDao.update(user, Secao.getInstancia().getUsuarioLogado().getId())){
                return true;
            } else {
                throw new Exception("Erro ao alterar usuario");
            }
        }
        return false;
    }

    public boolean excluir() throws Exception {
         if (usuarioDao.delete(Secao.getInstancia().getUsuarioLogado().getId())) {
             Secao.getInstancia().setUsuarioLogado(null);
             return true;
         } else {
             throw new Exception("Erro ao excluir usuario");
         }
    }

    public boolean validaCampos(String nome, String email, String senha, Operacao operacao) {
        if (nome.trim().isEmpty() || email.trim().isEmpty() || (senha.trim().isEmpty() && operacao == Operacao.INCLUIR)) {
            throw new IllegalArgumentException("Todos os campos são obrigatórios");
        }
        if (!ValidaEmail.validarEmail(email)) {
            throw new IllegalArgumentException("Email inválido");
        }
        return true;
    }
}
