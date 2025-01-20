/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.saam.teste.pratico.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author jonat
 */
public class Usuario extends Model {

    private String nome;
    private String email;
    private String senha;

    public Usuario () {}

    public Usuario(String nome, String email, String senha) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }



    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.nome);
        hash = 17 * hash + Objects.hashCode(this.email);
        hash = 17 * hash + Objects.hashCode(this.senha);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Usuario other = (Usuario) obj;
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        return Objects.equals(this.senha, other.senha);
    }

    @Override
    public String toString() {
        return "Usuario{" + "nome=" + nome + ", email=" + email + '}';
    }

    @Override
    public String getTableName() {
        return "Usuario";
    }


    @Override
    public List<String> getFieldNames() {
        List listaDeAtributos = new ArrayList<String>();
        listaDeAtributos.add("id");
        listaDeAtributos.add("Nome");
        listaDeAtributos.add("Email");
        listaDeAtributos.add("Senha");
        return listaDeAtributos;
    }
}
