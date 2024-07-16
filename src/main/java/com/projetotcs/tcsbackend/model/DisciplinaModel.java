package com.projetotcs.tcsbackend.model;

import jakarta.persistence.*;


@Entity
@Table(name="disciplina")
public class DisciplinaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column()
    private String nome;

    @Column()
    private Integer cargaHoraria;

    @Column
    private String codigoCor;

    @ManyToOne()
    @JoinColumn(name = "fase_id")
    private FaseModel fase;


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

    public Integer getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(Integer cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public String getCodigoCor() {
        return codigoCor;
    }

    public void setCodigoCor(String codigoCor) {
        this.codigoCor = codigoCor;
    }

    public FaseModel getFase() {
        return fase;
    }

    public void setFase(FaseModel fase) {
        this.fase = fase;
    }
}
