package com.hospital.hospitalcrud.controlador;

import com.hospital.hospitalcrud.entidad.Paciente;
import com.hospital.hospitalcrud.repositorio.PacienteRepositorio;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/pacientes")
public class PacienteControlador {

    private final PacienteRepositorio repositorio;

    public PacienteControlador(PacienteRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    // Obtener todos los pacientes
    @GetMapping
    public List<Paciente> obtenerTodos() {
        return repositorio.findAll();
    }

    // Crear paciente
    @PostMapping
    public Paciente crear(@RequestBody Paciente paciente) {
        return repositorio.save(paciente);
    }

    // Obtener paciente por ID (para cargar en el modal de edición)
    @GetMapping("/{id}")
    public ResponseEntity<Paciente> obtenerPorId(@PathVariable Long id) {
        Optional<Paciente> paciente = repositorio.findById(id);
        return paciente.map(ResponseEntity::ok)
                       .orElse(ResponseEntity.notFound().build());
    }

    // Actualizar paciente
    @PutMapping("/{id}")
    public ResponseEntity<Paciente> actualizar(@PathVariable Long id, @RequestBody Paciente pacienteNuevo) {
        Optional<Paciente> pacienteExistente = repositorio.findById(id);

        if (pacienteExistente.isPresent()) {
            Paciente paciente = pacienteExistente.get();
            paciente.setNombre(pacienteNuevo.getNombre());
            paciente.setEdad(pacienteNuevo.getEdad());
            paciente.setGenero(pacienteNuevo.getGenero());
            paciente.setDiagnostico(pacienteNuevo.getDiagnostico());
            return ResponseEntity.ok(repositorio.save(paciente));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar paciente
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (repositorio.existsById(id)) {
            repositorio.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
