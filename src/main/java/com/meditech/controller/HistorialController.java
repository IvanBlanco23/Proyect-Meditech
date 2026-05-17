package com.meditech.controller;

import com.meditech.database.ConsultaDAO;
import com.meditech.view.HistorialView;

public class HistorialController {

    private final HistorialView view;
    private final ConsultaDAO dao= new ConsultaDAO();

    public HistorialController(HistorialView view) {
        this.view = view;
    }

    public void cargarHistorial(int expedienteId){

        view.tabla.getItems().clear();
        view.tabla.getItems().addAll(
                dao.listarPorExpediente(expedienteId)
        );
    }
}
