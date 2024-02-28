package com.projetotcs.tcsbackend.model;

import java.util.List;


//@Entity(name= professor)
public class Professor {
   

    private Long id;
    private String nomeCompleto;
    private String telefone;
    private Integer qtdeDeDiasLecionaveis;
    private List<String> disponibilidadeNaSemana;


    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }


    public String getNomeCompleto() {
        return nomeCompleto;
    }
    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }


    public String getTelefone() {
        return telefone;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Integer getQtdeDeDiasLecionaveis() {
        return qtdeDeDiasLecionaveis;
    }
    public void setQtdeDeDiasLecionaveis(Integer qtdeDeDiasLecionaveis) {
        this.qtdeDeDiasLecionaveis = qtdeDeDiasLecionaveis;
    }

    public List<String> getDisponibilidadeNaSemana() {
        return disponibilidadeNaSemana;
    }
    public void setDisponibilidadeNaSemana(List<String> disponibilidadeNaSemana) {
        this.disponibilidadeNaSemana = disponibilidadeNaSemana;
    }
   


    
    
}
