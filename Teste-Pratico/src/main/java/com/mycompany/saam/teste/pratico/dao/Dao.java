/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.saam.teste.pratico.dao;

import com.mycompany.saam.teste.pratico.model.Model;

import java.util.List;

/**
 * @param <T>
 * @author jonat
 */
public interface Dao<T extends Model> {

    public Boolean insert(T model);

    public List<T> select();

    public T selectById(String id);

    public Boolean update(T model, String id);

    public Boolean delete(String id);
}
