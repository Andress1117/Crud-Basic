package com.hospital.hospitalcrud.controlador;

import com.hospital.hospitalcrud.entidad.Doctor;
import com.hospital.hospitalcrud.repositorio.DoctorRepositorio;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/doctores")
public class DoctorControlador {

    private final DoctorRepositorio repositorio;

    public DoctorControlador(DoctorRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    @GetMapping
    public List<Doctor> obtenerTodos() {
        return repositorio.findAll();
    }

    @PostMapping
    public Doctor crear(@RequestBody Doctor doctor) {
        return repositorio.save(doctor);
    }

    @PutMapping("/{id}")
    public Doctor actualizar(@PathVariable Long id, @RequestBody Doctor doctor) {
        doctor.setId(id);
        return repositorio.save(doctor);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        repositorio.deleteById(id);
    }
}
