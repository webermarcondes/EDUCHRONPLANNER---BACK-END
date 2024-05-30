package com.projetotcs.tcsbackend.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="curso")
public class CursoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nome;

    @ManyToOne()
    @JoinColumn(name="usuario_coord_id")
    private UsuarioModel usuarioCoordenador;

    @OneToMany(mappedBy = "curso")
    @JsonIgnore
    private List<FaseModel> fases;

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

    public Integer getQtdeFases() {
        return qtdeFases;
    }

    public void setQtdeFases(Integer qtdeFases) {
        this.qtdeFases = qtdeFases;
    }

    public UsuarioModel getUsuarioCoordenador() {
        return usuarioCoordenador;
    }

    public void setUsuarioCoordenador(UsuarioModel usuarioCoordenador) {
        this.usuarioCoordenador = usuarioCoordenador;
    }

    public List<FaseModel> getFases() {
        return fases;
    }

    public void setFases(List<FaseModel> fases) {
        this.fases = fases;
    }
}
