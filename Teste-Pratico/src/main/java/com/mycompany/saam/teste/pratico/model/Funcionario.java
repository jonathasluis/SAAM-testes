/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.saam.teste.pratico.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author jonat
 */
public class Funcionario extends Model {

    private String nome;
    private Date dataAdmissao;
    private Double valorSalario;
    private Boolean status;
    private String idUsuario;

    public Funcionario() {
    }
    public Funcionario(String nome, Date dataAdmissao, Double valorSalario, Boolean status, String idUsuario) {
        this.nome = nome;
        this.dataAdmissao = dataAdmissao;
        this.valorSalario = valorSalario;
        this.status = status;
        this.idUsuario = idUsuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataAdmissao() {
        return dataAdmissao;
    }

    public void setDataAdmissao(Date dataAdmissao) {
        this.dataAdmissao = dataAdmissao;
    }

    public Double getValorSalario() {
        return valorSalario;
    }

    public void setValorSalario(Double valorSalario) {
        this.valorSalario = valorSalario;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.nome);
        hash = 67 * hash + Objects.hashCode(this.dataAdmissao);
        hash = 67 * hash + Objects.hashCode(this.valorSalario);
        hash = 67 * hash + Objects.hashCode(this.status);
        hash = 67 * hash + Objects.hashCode(this.idUsuario);
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
        final Funcionario other = (Funcionario) obj;
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.dataAdmissao, other.dataAdmissao)) {
            return false;
        }
        if (!Objects.equals(this.valorSalario, other.valorSalario)) {
            return false;
        }
        return Objects.equals(this.status, other.status);
    }

    @Override
    public String toString() {
        return "Funcionario{" + "nome=" + nome + ", dataAdmissao=" + dataAdmissao + ", valorSalario=" + valorSalario + ", status=" + status + '}';
    }

        @Override
    public String getTableName() {
        return "Funcionario";
    }


    @Override
    public List<String> getFieldNames() {
        List listaDeAtributos = new ArrayList<String>();
        listaDeAtributos.add("Id");
        listaDeAtributos.add("Nome");
        listaDeAtributos.add("DataAdmissao");
        listaDeAtributos.add("ValorSalario");
        listaDeAtributos.add("Status");
        listaDeAtributos.add("usuarioID");
        return listaDeAtributos;
    }
}
