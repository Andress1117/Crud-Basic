package com.saludviva.crud.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Medicamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String dosis;

    @ManyToMany(mappedBy = "medicamentos")
    private List<Tratamiento> tratamientos;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getDosis() { return dosis; }
    public void setDosis(String dosis) { this.dosis = dosis; }
    public List<Tratamiento> getTratamientos() { return tratamientos; }
    public void setTratamientos(List<Tratamiento> tratamientos) { this.tratamientos = tratamientos; }
}
