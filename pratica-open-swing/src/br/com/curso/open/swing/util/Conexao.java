package br.com.curso.open.swing.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jonat
 */
public abstract class Conexao {

    private static Connection conexao;

    public static Connection getConexao() {

        try {
            if (conexao == null || conexao.isClosed()) {

                conecta();
            }
        } catch (SQLException ex) {

            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return conexao;
    }

    protected static void conecta() {
        try {
            Properties connectionProperties = ReadProperties.getConnectionProperties();
            String url = connectionProperties.getProperty("URL");
            String user = connectionProperties.getProperty("USER");
            String password = connectionProperties.getProperty("PASSWORD");
            conexao = (Connection) DriverManager.getConnection(url, user, password);
        } catch (SQLException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void desconecta() {

        try {
            if (conexao != null) {

                conexao.close();
            }
        } catch (SQLException ex) {

            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
