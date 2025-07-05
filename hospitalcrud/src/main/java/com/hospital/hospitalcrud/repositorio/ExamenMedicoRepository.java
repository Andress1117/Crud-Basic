package com.hospital.hospitalcrud.repositorio;

import com.hospital.hospitalcrud.entidad.ExamenMedico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamenMedicoRepository extends JpaRepository<ExamenMedico, Long> {
}
