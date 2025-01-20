/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.saam.teste.pratico.util;

import com.mycompany.saam.teste.pratico.model.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author jonat
 */
public class SqlGeneration {

    public static String insertSql(Model table) {
        return insertSql(table, table.getFieldNames());
    }

    public static String insertSql(Model table, List<String> campos) {
        String uuid = UUID.randomUUID().toString();
        StringBuilder sqlInsert = new StringBuilder("INSERT INTO ")
                .append(table.getTableName())
                .append(" (")
                .append(String.join(", ", campos))
                .append(") VALUES (")
                .append("\'"+ uuid +"\',");

        List<String> placeHolders = new ArrayList<>();
        campos.subList(1, campos.size()).forEach(campo -> placeHolders.add("?"));
        sqlInsert.append(String.join(", ", placeHolders))
                 .append(");");
        table.setId(uuid);
        return sqlInsert.toString();
    }

    public static String selectSql(Model table) {
        return selectSql(table, table.getFieldNames(), null);
    }

    public static String selectSql(Model table, String where) {
        return selectSql(table, table.getFieldNames(), where);
    }

    public static String selectSql(Model table, List<String> campos, String where) {
        StringBuilder sqlSelect = new StringBuilder("SELECT ")
                .append(String.join(", ", campos))
                .append(" FROM ")
                .append(table.getTableName())
                .append(" ");
        if (where != null && !where.trim().isEmpty()) {
            sqlSelect.append(where);
        }
        sqlSelect.append(";");
        return sqlSelect.toString();
    }

    public static String updateSql(Model table) {
        return updateSql(table, table.getFieldNames());
    }

    public static String updateSql(Model table, List<String> campos) {
        StringBuilder sqlUpdate = new StringBuilder("UPDATE ")
                .append(table.getTableName())
                .append(" SET ");
        List<String> setValues = new ArrayList<>();
        campos.subList(1, campos.size()).forEach(campo -> setValues.add(campo + " = ?"));
        sqlUpdate.append(String.join(", ", setValues))
                 .append(" WHERE id = ?::uuid;");

        return sqlUpdate.toString();
    }

    public static String deleteSql(Model table) {
        StringBuilder sqlDelete = new StringBuilder("DELETE FROM ")
                .append(table.getTableName())
                .append(" WHERE id = ?::uuid;");
        return sqlDelete.toString();
    }

}
