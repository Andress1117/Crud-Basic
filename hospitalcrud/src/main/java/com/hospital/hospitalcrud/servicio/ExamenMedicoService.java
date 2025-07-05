package com.hospital.hospitalcrud.servicio;

import com.hospital.hospitalcrud.entidad.ExamenMedico;
import com.hospital.hospitalcrud.repositorio.ExamenMedicoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExamenMedicoService {

    private final ExamenMedicoRepository repositorio;

    public ExamenMedicoService(ExamenMedicoRepository repositorio) {
        this.repositorio = repositorio;
    }

    public List<ExamenMedico> obtenerTodos() {
        return repositorio.findAll();
    }

    public Optional<ExamenMedico> obtenerPorId(Long id) {
        return repositorio.findById(id);
    }

    public ExamenMedico guardar(ExamenMedico examen) {
        return repositorio.save(examen);
    }

    public void eliminar(Long id) {
        repositorio.deleteById(id);
    }
}
