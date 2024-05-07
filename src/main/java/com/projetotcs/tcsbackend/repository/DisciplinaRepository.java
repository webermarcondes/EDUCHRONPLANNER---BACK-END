package com.projetotcs.tcsbackend.repository;

import com.projetotcs.tcsbackend.model.Disciplina;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DisciplinaRepository extends JpaRepository<Disciplina, Long> {

    List<Disciplina> findByFaseId(Long faseId);
}
