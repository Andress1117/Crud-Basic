package com.hospital.hospitalcrud.servicio;

import com.hospital.hospitalcrud.entidad.CitaMedica;
import com.hospital.hospitalcrud.repositorio.CitaMedicaRepositorio;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CitaMedicaServicio {

    private final CitaMedicaRepositorio repositorio;

    public CitaMedicaServicio(CitaMedicaRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    public List<CitaMedica> obtenerTodas() {
        return repositorio.findAll();
    }

    public CitaMedica guardar(CitaMedica cita) {
        return repositorio.save(cita);
    }

    public CitaMedica actualizar(Long id, CitaMedica cita) {
        cita.setId(id);
        return repositorio.save(cita);
    }

    public void eliminar(Long id) {
        repositorio.deleteById(id);
    }
}
