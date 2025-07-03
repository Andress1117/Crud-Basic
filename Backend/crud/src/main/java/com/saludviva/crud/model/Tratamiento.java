package com.saludviva.crud.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Tratamiento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descripcion;

    @ManyToMany(mappedBy = "tratamientos")
    private List<Diagnostico> diagnosticos;

    @ManyToMany
    private List<Medicamento> medicamentos;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public List<Diagnostico> getDiagnosticos() { return diagnosticos; }
    public void setDiagnosticos(List<Diagnostico> diagnosticos) { this.diagnosticos = diagnosticos; }
    public List<Medicamento> getMedicamentos() { return medicamentos; }
    public void setMedicamentos(List<Medicamento> medicamentos) { this.medicamentos = medicamentos; }
}
