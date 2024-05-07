package com.projetotcs.tcsbackend.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

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

    @ManyToOne()
    @JoinColumn(name="usuario_coord_id")
    private Usuario usuarioCoordenador;

    @OneToMany(mappedBy = "curso")
    @JsonIgnore
    private List<Fase> fases;


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

    public Usuario getUsuarioCoordenador() {
        return usuarioCoordenador;
    }

    public void setUsuarioCoordenador(Usuario usuarioCoordenador) {
        this.usuarioCoordenador = usuarioCoordenador;
    }

    public List<Fase> getFases() {
        return fases;
    }

    public void setFases(List<Fase> fases) {
        this.fases = fases;
    }
}
