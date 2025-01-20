package com.mycompany.saam.teste.pratico.util;

import com.mycompany.saam.teste.pratico.model.Usuario;

public class Secao {

    private static Secao instancia;
    private Usuario usuarioLogado;

    private Secao() {
        // Construtor privado para evitar instanciação direta
    }

    public static Secao getInstancia() {
        if (instancia == null) {
            instancia = new Secao();
        }
        return instancia;
    }

    public void setUsuarioLogado(Usuario usuario) {
        this.usuarioLogado = usuario;
    }

    public Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

    public boolean isUsuarioLogado() {
        return usuarioLogado != null;
    }

}
