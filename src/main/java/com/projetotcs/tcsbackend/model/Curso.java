package com.projetotcs.tcsbackend.model;

public class Curso {

    private Long id;
    private String nome;
    private Integer qtdeFases;

    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public Integer getQtdeFases() {
        return qtdeFases;
    }
    public void setQtdeFases(Integer qtdeFases) {
        this.qtdeFases = qtdeFases;
    }

    
}
