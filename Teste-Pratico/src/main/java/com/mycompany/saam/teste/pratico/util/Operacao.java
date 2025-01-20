package com.mycompany.saam.teste.pratico.util;

public enum Operacao {
    INCLUIR("Incluir"),
    ALTERAR("Alterar"),
    EXCLUIR("Excluir"),
    EXIBIR("Exibir");

    private String operacaoDescricao;

    Operacao(String operacao) {
        this.operacaoDescricao = operacao;
    }

    public String getOperacaoDescricao() {
        return operacaoDescricao;
    }


}
