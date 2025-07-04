package com.hospital.hospitalcrud.controlador;

import com.hospital.hospitalcrud.entidad.Paciente;
import com.hospital.hospitalcrud.repositorio.PacienteRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/pacientes")
public class PacienteControlador {

    @Autowired
    private PacienteRepositorio repositorio;

    @GetMapping
    public List<Paciente> listar() {
        return repositorio.findAll();
    }

    @PostMapping
    public Paciente guardar(@RequestBody Paciente paciente) {
        return repositorio.save(paciente);
    }

    @PutMapping("/{id}")
    public Paciente actualizar(@PathVariable Long id, @RequestBody Paciente paciente) {
        paciente.setId(id);
        return repositorio.save(paciente);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        repositorio.deleteById(id);
    }
}
