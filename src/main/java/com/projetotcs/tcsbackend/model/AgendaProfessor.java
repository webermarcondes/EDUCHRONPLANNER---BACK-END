package com.projetotcs.tcsbackend.model;


import jakarta.persistence.*;


/*
A agenda professor deve ser preenchida da seguinte forma:

No cadastro de professor:  professor e dia;
No cadastro de disciplina: disciplina e sala
 */
@Entity
@Table(name="agendaProfessor")
public class AgendaProfessor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne()
    @JoinColumn(name="professor_id")
    private Professor professor;

    @ManyToOne()
    @JoinColumn(name="diaDaSemana_id", nullable=false)
    private DiaDaSemana diaDaSemana;

    @ManyToOne()
    @JoinColumn(name="disciplina_id")
    private Disciplina disciplina;


    @ManyToOne()
    @JoinColumn(name = "sala_id")
    private Sala sala;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }
}
