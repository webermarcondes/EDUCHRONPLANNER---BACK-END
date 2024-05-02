package com.projetotcs.tcsbackend.model;


import jakarta.persistence.*;

@Entity
@Table(name="curso")
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nome;

    @Column
    private Integer horasTotais;

    //private Usuario coordenador;


    //Verificar viabilidade do Atributo abaixo:
    @Column
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

    public Integer getHorasTotais() {
        return horasTotais;
    }

    public void setHorasTotais(Integer horasTotais) {
        this.horasTotais = horasTotais;
    }

    public Integer getQtdeFases() {
        return qtdeFases;
    }

    public void setQtdeFases(Integer qtdeFases) {
        this.qtdeFases = qtdeFases;
    }
}
