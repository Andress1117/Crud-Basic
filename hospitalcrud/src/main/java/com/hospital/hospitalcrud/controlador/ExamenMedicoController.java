package com.hospital.hospitalcrud.controlador;

import com.hospital.hospitalcrud.entidad.ExamenMedico;
import com.hospital.hospitalcrud.servicio.ExamenMedicoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/examenes")
@CrossOrigin(origins = "*")
public class ExamenMedicoController {

    private final ExamenMedicoService servicio;

    public ExamenMedicoController(ExamenMedicoService servicio) {
        this.servicio = servicio;
    }

    @GetMapping
    public List<ExamenMedico> listarTodos() {
        return servicio.obtenerTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExamenMedico> obtenerPorId(@PathVariable Long id) {
        return servicio.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ExamenMedico crear(@RequestBody ExamenMedico examen) {
        return servicio.guardar(examen);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExamenMedico> actualizar(@PathVariable Long id, @RequestBody ExamenMedico examen) {
        return servicio.obtenerPorId(id).map(existente -> {
            examen.setId(id);
            return ResponseEntity.ok(servicio.guardar(examen));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (servicio.obtenerPorId(id).isPresent()) {
            servicio.eliminar(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
