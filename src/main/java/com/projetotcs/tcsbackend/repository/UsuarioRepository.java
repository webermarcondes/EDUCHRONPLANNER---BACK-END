package com.projetotcs.tcsbackend.repository;

import com.projetotcs.tcsbackend.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
