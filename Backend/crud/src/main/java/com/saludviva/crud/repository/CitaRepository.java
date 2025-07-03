package com.saludviva.crud.repository;

import com.saludviva.crud.model.Cita;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CitaRepository extends JpaRepository<Cita, Long> {
}
