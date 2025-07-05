package com.hospital.hospitalcrud.servicio;

import com.hospital.hospitalcrud.dto.PacienteDTO;
import com.hospital.hospitalcrud.entidad.Paciente;
import com.hospital.hospitalcrud.repositorio.PacienteRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PacienteServicioImpl implements PacienteServicio {

    @Autowired
    private PacienteRepositorio pacienteRepositorio;

    private PacienteDTO mapToDTO(Paciente paciente) {
        PacienteDTO dto = new PacienteDTO();
        dto.setId(paciente.getId());
        dto.setNombre(paciente.getNombre());
        dto.setEdad(paciente.getEdad());
        dto.setGenero(paciente.getGenero());
        dto.setDiagnostico(paciente.getDiagnostico());
        return dto;
    }

    private Paciente mapToEntidad(PacienteDTO dto) {
        Paciente paciente = new Paciente();
        paciente.setId(dto.getId());
        paciente.setNombre(dto.getNombre());
        paciente.setEdad(dto.getEdad());
        paciente.setGenero(dto.getGenero());
        paciente.setDiagnostico(dto.getDiagnostico());
        return paciente;
    }

    @Override
    public List<PacienteDTO> obtenerTodos() {
        return pacienteRepositorio.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PacienteDTO obtenerPorId(Long id) {
        return pacienteRepositorio.findById(id)
                .map(this::mapToDTO)
                .orElse(null);
    }

    @Override
    public PacienteDTO guardar(PacienteDTO dto) {
        Paciente paciente = mapToEntidad(dto);
        return mapToDTO(pacienteRepositorio.save(paciente));
    }

    @Override
    public PacienteDTO actualizar(Long id, PacienteDTO dto) {
        Paciente paciente = pacienteRepositorio.findById(id).orElse(null);
        if (paciente == null) return null;

        paciente.setNombre(dto.getNombre());
        paciente.setEdad(dto.getEdad());
        paciente.setGenero(dto.getGenero());
        paciente.setDiagnostico(dto.getDiagnostico());

        return mapToDTO(pacienteRepositorio.save(paciente));
    }

    @Override
    public void eliminar(Long id) {
        pacienteRepositorio.deleteById(id);
    }
}
