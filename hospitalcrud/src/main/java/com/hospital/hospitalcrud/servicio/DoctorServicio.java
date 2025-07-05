package com.hospital.hospitalcrud.servicio;

import com.hospital.hospitalcrud.dto.DoctorDTO;
import com.hospital.hospitalcrud.entidad.Doctor;
import com.hospital.hospitalcrud.repositorio.DoctorRepositorio;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DoctorServicio {

    private final DoctorRepositorio repositorio;

    public DoctorServicio(DoctorRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    public List<DoctorDTO> obtenerTodos() {
        List<Doctor> doctores = repositorio.findAll();
        return doctores.stream()
                .map(doc -> new DoctorDTO(doc.getId(), doc.getNombre(), doc.getEspecialidad(), doc.getCorreo()))
                .collect(Collectors.toList());
    }

    public Doctor guardar(Doctor doctor) {
        return repositorio.save(doctor);
    }

    public Doctor actualizar(Long id, Doctor doctor) {
        doctor.setId(id);
        return repositorio.save(doctor);
    }

    public void eliminar(Long id) {
        repositorio.deleteById(id);
    }
}
