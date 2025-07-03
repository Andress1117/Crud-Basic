package com.saludviva.crud.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Especialidad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;

    @OneToMany(mappedBy = "especialidad")
    private List<Medico> medicos;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public List<Medico> getMedicos() { return medicos; }
    public void setMedicos(List<Medico> medicos) { this.medicos = medicos; }
}