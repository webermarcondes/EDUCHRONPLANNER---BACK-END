package com.projetotcs.tcsbackend.repository;

import com.projetotcs.tcsbackend.model.SalaModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SalaRepository  extends JpaRepository<SalaModel, Long> {

    Optional<SalaModel> findByNumero(Integer numero);
}
