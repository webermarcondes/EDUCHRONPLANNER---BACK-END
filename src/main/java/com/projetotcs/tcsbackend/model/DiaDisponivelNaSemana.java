package com.projetotcs.tcsbackend.model;



import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.projetotcs.tcsbackend.enums.DiaDaSemana;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="DiaDisponivelNaSemana")
public class DiaDisponivelNaSemana {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @ManyToMany(mappedBy="diasDisponiveisnaSemana")
    @JsonIgnore  //Ignora a serialização e evita recursão infinita;
    public List<Professor> professores;


    @Column()
    public DiaDaSemana diaDaSemana;


    public DiaDaSemana getDiaDaSemana() {
        return diaDaSemana;
    }


    public void setDiaDaSemana(DiaDaSemana diaDaSemana) {
        this.diaDaSemana = diaDaSemana;
    }


    @Override
    public String toString() {
        return "DiaDisponivelNaSemana [id=" + id + ", diaDaSemana=" + diaDaSemana + "]";
    }

    
    


    

    

}
