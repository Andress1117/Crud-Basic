package com.hospital.hospitalcrud.controlador;

import com.hospital.hospitalcrud.entidad.CitaMedica;
import com.hospital.hospitalcrud.servicio.CitaMedicaServicio;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/citas")
@CrossOrigin(origins = "*")
public class CitaMedicaControlador {

    private final CitaMedicaServicio servicio;

    public CitaMedicaControlador(CitaMedicaServicio servicio) {
        this.servicio = servicio;
    }

    @GetMapping
    public List<CitaMedica> obtenerTodas() {
        return servicio.obtenerTodas();
    }

    @PostMapping
    public CitaMedica guardar(@RequestBody CitaMedica cita) {
        return servicio.guardar(cita);
    }

    @PutMapping("/{id}")
    public CitaMedica actualizar(@PathVariable Long id, @RequestBody CitaMedica cita) {
        return servicio.actualizar(id, cita);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        servicio.eliminar(id);
    }
}
