package com.hospital.hospitalcrud.repositorio;

import com.hospital.hospitalcrud.entidad.Medicamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicamentoRepository extends JpaRepository<Medicamento, Long> {
}
