package com.hospital.hospitalcrud.servicio;

import com.hospital.hospitalcrud.dto.PacienteDTO;

import java.util.List;

public interface PacienteServicio {
    List<PacienteDTO> obtenerTodos();
    PacienteDTO obtenerPorId(Long id);
    PacienteDTO guardar(PacienteDTO pacienteDTO);
    PacienteDTO actualizar(Long id, PacienteDTO pacienteDTO);
    void eliminar(Long id);
}
