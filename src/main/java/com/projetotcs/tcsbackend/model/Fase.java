package com.projetotcs.tcsbackend.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="fase")
public class Fase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column()
    private Integer numero;

    @OneToMany(mappedBy = "fase")
    private List<Disciplina> disciplinas;

    //Curso curso;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }
}
