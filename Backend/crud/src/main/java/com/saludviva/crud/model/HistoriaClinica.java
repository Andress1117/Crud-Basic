package com.saludviva.crud.model;

import jakarta.persistence.*;

@Entity
public class HistoriaClinica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String observaciones;

    @OneToOne
    private Cita cita;

    @ManyToOne
    private Diagnostico diagnostico;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getObservaciones() { return observaciones; }
    public void setObservaciones(String observaciones) { this.observaciones = observaciones; }
    public Cita getCita() { return cita; }
    public void setCita(Cita cita) { this.cita = cita; }
    public Diagnostico getDiagnostico() { return diagnostico; }
    public void setDiagnostico(Diagnostico diagnostico) { this.diagnostico = diagnostico; }
}
