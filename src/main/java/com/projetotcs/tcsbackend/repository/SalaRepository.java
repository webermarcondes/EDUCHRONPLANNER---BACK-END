package com.projetotcs.tcsbackend.repository;

import com.projetotcs.tcsbackend.model.Sala;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalaRepository  extends JpaRepository<Sala, Long> {

    Sala findByNumero(Integer numero);
}
