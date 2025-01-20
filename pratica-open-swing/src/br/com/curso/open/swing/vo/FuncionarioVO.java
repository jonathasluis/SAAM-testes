/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.curso.open.swing.vo;

import java.math.BigDecimal;
import java.util.Date;
import org.openswing.swing.message.receive.java.ValueObjectImpl;

/**
 *
 * @author jonat
 */
public class FuncionarioVO extends ValueObjectImpl {

    private Integer id;
    private String nome;
    private String sexo;
    private BigDecimal salario;
    private Date data_de_entrada;
    private Date data_de_saida;
    private String cidade;
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String uf;
    private String cep;
    private String cargo;
    private Integer fk_cargo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public Date getData_de_entrada() {
        return data_de_entrada;
    }

    public void setData_de_entrada(Date data_de_entrada) {
        this.data_de_entrada = data_de_entrada;
    }

    public Date getData_de_saida() {
        return data_de_saida;
    }

    public void setData_de_saida(Date data_de_saida) {
        this.data_de_saida = data_de_saida;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public Integer getFk_cargo() {
        return fk_cargo;
    }

    public void setFk_cargo(Integer fk_cargo) {
        this.fk_cargo = fk_cargo;
    }

    @Override
    public String toString() {
        return "FuncionarioVO{" + "id=" + id + ", nome=" + nome + ", sexo=" + sexo + ", salario=" + salario + ", data_de_entrada=" + data_de_entrada + ", data_de_saida=" + data_de_saida + ", cidade=" + cidade + ", logradouro=" + logradouro + ", numero=" + numero + ", complemento=" + complemento + ", bairro=" + bairro + ", uf=" + uf + ", cep=" + cep + ", cargo=" + cargo + ", fk_cargo=" + fk_cargo + '}';
    }
}
