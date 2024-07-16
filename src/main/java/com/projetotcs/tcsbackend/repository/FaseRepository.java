package com.projetotcs.tcsbackend.repository;

import com.projetotcs.tcsbackend.model.CursoModel;
import com.projetotcs.tcsbackend.model.FaseModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FaseRepository extends JpaRepository<FaseModel, Long> {

    FaseModel findByNumero(Integer numeroFase);
    List<FaseModel> getFasesByCurso(CursoModel cursoModel);
}
