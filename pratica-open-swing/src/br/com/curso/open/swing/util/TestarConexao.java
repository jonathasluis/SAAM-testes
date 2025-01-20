/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.curso.open.swing.util;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Leonel
 */
public class TestarConexao {

    public TestarConexao() {

        PreparedStatement ps = null;
        try {

            ps = Conexao.getConexao().prepareStatement("delete from cargo");
//            ps = Conexao.getConexao().prepareStatement("INSERT INTO \n"
//                    + "  public.cargo\n"
//                    + "(\n"
//                    + "  cargo\n"
//                    + ")\n"
//                    + "VALUES (\n"
//                    + "  \n"
//                    + "  'suporte'\n"
//                    + ");");
            ps.executeUpdate();
        } catch (SQLException ex) {

            Logger.getLogger(TestarConexao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {

                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException ex) {

                Logger.getLogger(TestarConexao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public static void main(String[] args) {

        try {
            if (Conexao.getConexao().isClosed()) {

                System.err.println("Conexao está fechada");
            } else {

                System.err.println("Conexao está aberta");
            }
        } catch (SQLException ex) {
            Logger.getLogger(TestarConexao.class.getName()).log(Level.SEVERE, null, ex);
        }

        new TestarConexao();
    }
}
