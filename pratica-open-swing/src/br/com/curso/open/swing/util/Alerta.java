/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.curso.open.swing.util;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author jonat
 */

public class Alerta {

    ImageIcon iconeSucesso = new javax.swing.ImageIcon(getClass().getResource("/br/com/curso/open/swing/img/iconeSucesso.png"));
    ImageIcon iconeErro = new javax.swing.ImageIcon(getClass().getResource("/br/com/curso/open/swing/img/iconeErro.png"));
    ImageIcon iconePergunta = new javax.swing.ImageIcon(getClass().getResource("/br/com/curso/open/swing/img/iconePergunta.png"));

    private static Alerta objAlerta;

    private Alerta() {
    }

    public static Alerta getInstance() {
        if (objAlerta == null) {
            objAlerta = new Alerta();
        }

        return objAlerta;
    }

    public void alertNormal(String mensagem) {

        JOptionPane.showMessageDialog(null, mensagem, "Aviso", 2);
    }

    public void alertSucesso(String mensagem) {

        JOptionPane.showMessageDialog(null, mensagem, "Sucesso", 1, iconeSucesso);
    }

    public void alertErro(String mensagem) {

        JOptionPane.showMessageDialog(null, mensagem, "Erro", 0, iconeErro);
    }

    public boolean alertPerguta(String pergunta) {

        return (JOptionPane.showConfirmDialog(null, pergunta, "Confirmação", JOptionPane.YES_NO_OPTION, 1, iconePergunta) == JOptionPane.YES_OPTION);
    }
}
