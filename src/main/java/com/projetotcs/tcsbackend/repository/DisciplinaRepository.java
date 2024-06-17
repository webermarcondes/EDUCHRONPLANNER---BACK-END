package com.projetotcs.tcsbackend.repository;

import com.projetotcs.tcsbackend.model.DisciplinaModel;
import com.projetotcs.tcsbackend.model.FaseModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DisciplinaRepository extends JpaRepository<DisciplinaModel, Long> {

    List<DisciplinaModel> findByFase(FaseModel fase);
}
