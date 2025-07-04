package com.hospital.hospitalcrud.repositorio;

import com.hospital.hospitalcrud.entidad.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepositorio extends JpaRepository<Paciente, Long> {
}
