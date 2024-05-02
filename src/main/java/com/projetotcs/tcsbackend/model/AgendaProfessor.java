package com.projetotcs.tcsbackend.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name="agendaProfessor")
public class AgendaProfessor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne()
    @JoinColumn(name="professor_id", nullable = false)
    private Professor professor;

    @ManyToOne()
    @JoinColumn(name="diaDaSemana_id", nullable=false)
    private DiaDaSemana diaDaSemana;

    @ManyToOne()
    @JoinColumn(name="disciplina_id")
    private Disciplina disciplina;

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public DiaDaSemana getDiaDaSemana() {
        return diaDaSemana;
    }

    public void setDiaDaSemana(DiaDaSemana diaDaSemana) {
        this.diaDaSemana = diaDaSemana;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }
}
