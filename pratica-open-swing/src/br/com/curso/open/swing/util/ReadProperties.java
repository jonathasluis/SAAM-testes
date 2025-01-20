/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.curso.open.swing.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jonat
 */
public abstract class ReadProperties {

    private static Properties connectionProperties;

    public static Properties getConnectionProperties() {
        if (connectionProperties == null) {
            connectionProperties = readConectionProperties();
        }

        return connectionProperties;
    }

    private static Properties readConectionProperties() {
        Properties propriedades = new Properties();
        try (FileInputStream in = new FileInputStream("src/br/com/curso/open/swing/arquivos/conn.properties")) {
            propriedades.load(in);
        } catch (IOException ex) {
            Logger.getLogger(ReadProperties.class.getName()).log(Level.SEVERE, null, ex);
        }

        return propriedades;
    }
}
