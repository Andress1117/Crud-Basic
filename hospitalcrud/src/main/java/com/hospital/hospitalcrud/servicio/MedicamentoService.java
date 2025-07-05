package com.hospital.hospitalcrud.servicio;

import com.hospital.hospitalcrud.dto.MedicamentoDTO;
import com.hospital.hospitalcrud.entidad.Medicamento;
import com.hospital.hospitalcrud.repositorio.MedicamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MedicamentoService {

    @Autowired
    private MedicamentoRepository medicamentoRepository;

    public List<MedicamentoDTO> obtenerTodos() {
        return medicamentoRepository.findAll().stream().map(this::convertirADTO).collect(Collectors.toList());
    }

    public MedicamentoDTO guardar(MedicamentoDTO dto) {
        Medicamento medicamento = convertirAEntidad(dto);
        Medicamento guardado = medicamentoRepository.save(medicamento);
        return convertirADTO(guardado);
    }

    public MedicamentoDTO actualizar(Long id, MedicamentoDTO dto) {
        Medicamento existente = medicamentoRepository.findById(id).orElseThrow();
        existente.setNombre(dto.getNombre());
        existente.setDosis(dto.getDosis());
        existente.setIndicaciones(dto.getIndicaciones());
        existente.setVencimiento(dto.getVencimiento());
        return convertirADTO(medicamentoRepository.save(existente));
    }

    public void eliminar(Long id) {
        medicamentoRepository.deleteById(id);
    }

    private MedicamentoDTO convertirADTO(Medicamento medicamento) {
        MedicamentoDTO dto = new MedicamentoDTO();
        dto.setId(medicamento.getId());
        dto.setNombre(medicamento.getNombre());
        dto.setDosis(medicamento.getDosis());
        dto.setIndicaciones(medicamento.getIndicaciones());
        dto.setVencimiento(medicamento.getVencimiento());
        return dto;
    }

    private Medicamento convertirAEntidad(MedicamentoDTO dto) {
        Medicamento medicamento = new Medicamento();
        medicamento.setId(dto.getId());
        medicamento.setNombre(dto.getNombre());
        medicamento.setDosis(dto.getDosis());
        medicamento.setIndicaciones(dto.getIndicaciones());
        medicamento.setVencimiento(dto.getVencimiento());
        return medicamento;
    }
}
