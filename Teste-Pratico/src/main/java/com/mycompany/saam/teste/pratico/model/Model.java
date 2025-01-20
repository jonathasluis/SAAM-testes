/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.saam.teste.pratico.model;

import java.util.List;

/**
 *
 * @author jonat
 */
public abstract class Model {
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public abstract String getTableName();
    public abstract List<String> getFieldNames();
    
}
