package com.projetotcs.tcsbackend.repository;

import com.projetotcs.tcsbackend.model.AgendaProfessorModel;
import com.projetotcs.tcsbackend.model.FaseModel;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AgendaProfessorRepository extends JpaRepository<AgendaProfessorModel, Long> {

    List<AgendaProfessorModel> findByDisciplinaFase(FaseModel fase);
    Long countByDisciplinaId(Long disciplinaId);
}
