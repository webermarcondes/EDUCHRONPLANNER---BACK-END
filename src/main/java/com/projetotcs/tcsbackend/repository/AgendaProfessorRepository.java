package com.projetotcs.tcsbackend.repository;

import com.projetotcs.tcsbackend.model.AgendaProfessor;
import com.projetotcs.tcsbackend.model.DiaDaSemana;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AgendaProfessorRepository extends JpaRepository<AgendaProfessor, Long> {

    //Integer countByProfessor(Long professorId);

    List<AgendaProfessor> findByProfessorId(Long professorId);


    List<AgendaProfessor> findByDisciplinaId(Long disciplinaId);

    List<AgendaProfessor> findByDiaDaSemanaId(Long diaDaSemanaId);

    List<AgendaProfessor> findBySalaId(Long salaId);

    List<AgendaProfessor> findByProfessorIsNull();


}
