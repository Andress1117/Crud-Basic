package com.hospital.hospitalcrud.controlador;

import com.hospital.hospitalcrud.dto.MedicamentoDTO;
import com.hospital.hospitalcrud.servicio.MedicamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medicamentos")
@CrossOrigin(origins = "*")
public class MedicamentoController {

    @Autowired
    private MedicamentoService medicamentoService;

    @GetMapping
    public List<MedicamentoDTO> listarTodos() {
        return medicamentoService.obtenerTodos();
    }

    @PostMapping
    public MedicamentoDTO crear(@RequestBody MedicamentoDTO dto) {
        return medicamentoService.guardar(dto);
    }

    @PutMapping("/{id}")
    public MedicamentoDTO actualizar(@PathVariable Long id, @RequestBody MedicamentoDTO dto) {
        return medicamentoService.actualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        medicamentoService.eliminar(id);
    }
}
