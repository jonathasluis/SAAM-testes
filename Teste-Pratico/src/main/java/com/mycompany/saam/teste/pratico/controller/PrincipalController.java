package com.mycompany.saam.teste.pratico.controller;

import com.mycompany.saam.teste.pratico.util.Secao;

public class PrincipalController {

    public void logout() {
        Secao.getInstancia().setUsuarioLogado(null);
    }
}
