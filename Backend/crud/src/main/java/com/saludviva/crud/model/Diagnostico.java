package com.saludviva.crud.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Diagnostico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descripcion;

    @OneToMany(mappedBy = "diagnostico")
    private List<HistoriaClinica> historias;

    @ManyToMany
    private List<Tratamiento> tratamientos;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public List<HistoriaClinica> getHistorias() { return historias; }
    public void setHistorias(List<HistoriaClinica> historias) { this.historias = historias; }
    public List<Tratamiento> getTratamientos() { return tratamientos; }
    public void setTratamientos(List<Tratamiento> tratamientos) { this.tratamientos = tratamientos; }
}
