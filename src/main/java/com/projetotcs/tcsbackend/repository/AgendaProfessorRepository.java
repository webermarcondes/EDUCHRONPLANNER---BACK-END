package com.projetotcs.tcsbackend.repository;

import com.projetotcs.tcsbackend.model.AgendaProfessor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AgendaProfessorRepository extends JpaRepository<AgendaProfessor, Long> {

    //Integer countByProfessor(Long professorId);

    List<AgendaProfessor> findByProfessorId(Long professorId);


    List<AgendaProfessor> findByDisciplinaId(Long disciplinaId);

    List<AgendaProfessor> findByDiaDaSemanaId(Long diaDaSemanaId);
}
