package com.projetotcs.tcsbackend.model;


import java.util.List;

import com.projetotcs.tcsbackend.enums.Status;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;


/*Falta implementar a foto de perfil*/
@Entity
@Table(name="professor")
public class Professor {
   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false)
    private String nomeCompleto;

    @Column()
    private String telefone;
    
    @ManyToMany
    @JoinTable(name="professor_disponibilidade",
                joinColumns = @JoinColumn(name= "professor_id"),
                inverseJoinColumns = @JoinColumn(name="DiaDisponivelNaSemana_id"))
    private List<DiaDisponivelNaSemana> diasDisponiveisnaSemana;

    @Column()
    private Integer qtdeDiasDeAula;
    

    /*@Column()
    private byte[] fotoPerfil;*/

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

    
    public List<DiaDisponivelNaSemana> getDiasDisponiveisnaSemana() {
        return diasDisponiveisnaSemana;
    }
    public void setDiasDisponiveisnaSemana(List<DiaDisponivelNaSemana> diasDisponiveisnaSemana) {
        this.diasDisponiveisnaSemana = diasDisponiveisnaSemana;
    }

    
    /*public byte[] getFotoPerfil() {
        return fotoPerfil;
    }

    public void setFotoPerfil(byte[] fotoPerfil) {
        this.fotoPerfil = fotoPerfil;
    }*/


    public Status getStatus() {
        return status;
    }
    public void setStatus(Status status) {
        this.status = status;
    }

    





    

    
        
}
