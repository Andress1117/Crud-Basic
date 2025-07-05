package com.hospital.hospitalcrud.repositorio;

import com.hospital.hospitalcrud.entidad.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepositorio extends JpaRepository<Doctor, Long> {
}
