package com.meditech.model;

public class Expediente {

    private int idExpediente;
    private int idPaciente;
    private String alergias;
    private String antecedentes;
    private String tipoSangre;

    public Expediente() {
    }

    public Expediente(int idPaciente, String alergias, String antecedentes, String tipoSangre) {
        this.idPaciente = idPaciente;
        this.alergias = alergias;
        this.antecedentes = antecedentes;
        this.tipoSangre = tipoSangre;
    }

    public int getIdExpediente() {
        return idExpediente;
    }

    public void setIdExpediente(int idExpediente) {
        this.idExpediente = idExpediente;
    }

    public int getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }

    public String getAlergias() {
        return alergias;
    }

    public void setAlergias(String alergias) {
        this.alergias = alergias;
    }

    public String getAntecedentes() {
        return antecedentes;
    }

    public void setAntecedentes(String antecedentes) {
        this.antecedentes = antecedentes;
    }

    public String getTipoSangre() {
        return tipoSangre;
    }

    public void setTipoSangre(String tipoSangre) {
        this.tipoSangre = tipoSangre;
    }
}
