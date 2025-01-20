/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.curso.open.swing.util;

import java.util.Hashtable;
import org.openswing.swing.domains.java.Domain;

/**
 *
 * @author jonat
 */
public final class Dominios {

    private Hashtable domains;

    public Dominios(Hashtable domains) {

        this.domains = domains;
        criarDominio();
    }

    public void criarDominio() {

        /*Dominio de SEXO*/
        Domain sexo = new Domain("SEXO");
        sexo.addDomainPair("", "");
        sexo.addDomainPair("F", "Feminino");
        sexo.addDomainPair("M", "Masculino");
        domains.put(sexo.getDomainId(), sexo);

        /*Dominio de UF*/
        Domain uf = new Domain("UF");
        uf.addDomainPair("", "");
        uf.addDomainPair("AC", "AC - Acre");
        uf.addDomainPair("AL", "AL - Alagoas");
        uf.addDomainPair("AM", "AM - Amazonas");
        uf.addDomainPair("AP", "AP - Amapá");
        uf.addDomainPair("BA", "BA - Bahia");
        uf.addDomainPair("CE", "CE - Ceará");
        uf.addDomainPair("DF", "DF - Distrito Federal");
        uf.addDomainPair("ES", "ES - Espírito Santo");
        uf.addDomainPair("GO", "GO - Goiás");
        uf.addDomainPair("MA", "MA - Maranhão");
        uf.addDomainPair("MG", "MG - Minas Gerais");
        uf.addDomainPair("MS", "MS - Mato Grosso do Sul");
        uf.addDomainPair("MT", "MT - Mato Grosso");
        uf.addDomainPair("PA", "PA - Pará");
        uf.addDomainPair("PB", "PB - Paraíba");
        uf.addDomainPair("PE", "PE - Pernambuco");
        uf.addDomainPair("PI", "PI - Piauí");
        uf.addDomainPair("PR", "PR - Paraná");
        uf.addDomainPair("RJ", "RJ - Rio de Janeiro");
        uf.addDomainPair("RN", "RN - Rio Grande do Norte");
        uf.addDomainPair("RO", "RO - Rondônia");
        uf.addDomainPair("RR", "RR - Roraima");
        uf.addDomainPair("RS", "RS - Rio Grande do Sul");
        uf.addDomainPair("SC", "SC - Santa Catarina");
        uf.addDomainPair("SE", "SE - Sergipe");
        uf.addDomainPair("SP", "SP - São Paulo");
        uf.addDomainPair("TO", "TO - Tocantis");
        domains.put(uf.getDomainId(), uf);
    }
}
