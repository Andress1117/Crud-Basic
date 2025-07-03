package com.saludviva.crud.repository;

import com.saludviva.crud.model.Diagnostico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiagnosticoRepository extends JpaRepository<Diagnostico, Long> {
}
