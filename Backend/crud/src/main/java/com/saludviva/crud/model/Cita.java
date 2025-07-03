package com.saludviva.crud.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Cita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime fechaHora;

    @ManyToOne
    private Paciente paciente;

    @ManyToOne
    private Medico medico;

    @OneToOne(mappedBy = "cita", cascade = CascadeType.ALL)
    private HistoriaClinica historiaClinica;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public LocalDateTime getFechaHora() { return fechaHora; }
    public void setFechaHora(LocalDateTime fechaHora) { this.fechaHora = fechaHora; }
    public Paciente getPaciente() { return paciente; }
    public void setPaciente(Paciente paciente) { this.paciente = paciente; }
    public Medico getMedico() { return medico; }
    public void setMedico(Medico medico) { this.medico = medico; }
    public HistoriaClinica getHistoriaClinica() { return historiaClinica; }
    public void setHistoriaClinica(HistoriaClinica historiaClinica) { this.historiaClinica = historiaClinica; }
}