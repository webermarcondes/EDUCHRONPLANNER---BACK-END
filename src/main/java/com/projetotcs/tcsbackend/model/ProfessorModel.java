package com.projetotcs.tcsbackend.model;


import com.projetotcs.tcsbackend.enums.Status;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name="professor")
public class ProfessorModel {
   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false)
    private String nomeCompleto;

    @Column()
    private String telefone;

    @Column(unique = true, length = 11)
    private String cpf;

    @Column()
    private Integer qtdeDiasDeAula;


    @Column()
    private Status status;



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


    public Integer getQtdeDiasDeAula() {
        return qtdeDiasDeAula;
    }
    public void setQtdeDiasDeAula(Integer qtdeDiasDeAula) {
        this.qtdeDiasDeAula = qtdeDiasDeAula;
    }


    public Status getStatus() {
        return status;
    }
    public void setStatus(Status status) {
        this.status = status;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
