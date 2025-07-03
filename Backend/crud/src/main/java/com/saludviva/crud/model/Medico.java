package com.saludviva.crud.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Medico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String registroMedico;

    @ManyToOne
    private Especialidad especialidad;

    @OneToMany(mappedBy = "medico", cascade = CascadeType.ALL)
    private List<Cita> citas;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getRegistroMedico() { return registroMedico; }
    public void setRegistroMedico(String registroMedico) { this.registroMedico = registroMedico; }
    public Especialidad getEspecialidad() { return especialidad; }
    public void setEspecialidad(Especialidad especialidad) { this.especialidad = especialidad; }
    public List<Cita> getCitas() { return citas; }
    public void setCitas(List<Cita> citas) { this.citas = citas; }
}