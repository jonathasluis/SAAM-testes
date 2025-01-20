/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.saam.teste.pratico.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 *
 * @author jonat
 */
public class PostgresDBConnect {

    public static Connection connect() throws SQLException {
        String url = "jdbc:postgresql://localhost/saam_teste_pratico";
        String user = "postgres";
        String password = "root";

        return DriverManager.getConnection(url, user, password);
    }
}
