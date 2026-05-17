package com.meditech.model;

public class Cita {

    private int id;
    private int pacienteId;
    private String fecha;
    private String motivo;
    private String doctor;
    private int expedienteId;
    private EstadoCita estado;

    public Cita() {}

    public Cita(int pacienteId, String fecha, String motivo, String doctor) {
        this.pacienteId = pacienteId;
        this.fecha = fecha;
        this.motivo = motivo;
        this.doctor = doctor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPacienteId() {
        return pacienteId;
    }

    public void setPacienteId(int pacienteId) {
        this.pacienteId = pacienteId;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public int getExpedienteId() {
        return expedienteId;
    }

    public void setExpedienteId(int expedienteId) {
        this.expedienteId = expedienteId;
    }

    public EstadoCita getEstado() {
        return estado;
    }

    public void setEstado(EstadoCita estado) {
        this.estado = estado;
    }
}
