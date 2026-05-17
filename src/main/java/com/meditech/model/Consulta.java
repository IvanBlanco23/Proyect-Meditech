package com.meditech.model;

public class Consulta {

    private int idConsulta;
    private int expedienteId;
    private int doctorId;
    private String diagnostico;
    private String tratamiento;
    private String observaciones;
    private String fecha;

    public Consulta() {
    }

    public Consulta(int expedienteId, int doctorId, String diagnostico, String tratamiento, String observaciones, String fecha) {
        this.expedienteId = expedienteId;
        this.doctorId = doctorId;
        this.diagnostico = diagnostico;
        this.tratamiento = tratamiento;
        this.observaciones = observaciones;
        this.fecha = fecha;
    }

    public int getIdConsulta() {
        return idConsulta;
    }

    public void setIdConsulta(int idConsulta) {
        this.idConsulta = idConsulta;
    }

    public int getExpedienteId() {
        return expedienteId;
    }

    public void setExpedienteId(int expedienteId) {
        this.expedienteId = expedienteId;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public String getTratamiento() {
        return tratamiento;
    }

    public void setTratamiento(String tratamiento) {
        this.tratamiento = tratamiento;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
