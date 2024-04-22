package com.projetotcs.tcsbackend.model;


/* 
1	"SEGUNDA-FEIRA"
2	"TERÇA-FEIRA"
3	"QUARTA-FEIRA"
4	"QUINTA-FEIRA"
5	"SEXTA-FEIRA"
6	"SÁBADO"
*/


import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
//import com.projetotcs.tcsbackend.enums.DiaDaSemana;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="DiaDisponivelNaSemana")
public class DiaDisponivelNaSemana {
    
    @Id
    public Long id;

    @ManyToMany(mappedBy="diasDisponiveisnaSemana")
    @JsonIgnore  //Ignora a serialização e evita recursão infinita;
    public List<Professor> professores;


    @Column()
    public String diaDaSemana;


    

    public String getDiaDaSemana() {
        return diaDaSemana;
    }




    public void setDiaDaSemana(String diaDaSemana) {
        this.diaDaSemana = diaDaSemana;
    }




    @Override
    public String toString() {
        return "DiaDisponivelNaSemana [id=" + id + ", diaDaSemana=" + diaDaSemana + "]";
    }

    
    


    

    

}
