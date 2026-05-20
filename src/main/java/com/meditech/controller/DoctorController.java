package com.meditech.controller;

import com.meditech.database.CitaDAO;
import com.meditech.model.Cita;
import com.meditech.model.Consulta;
import com.meditech.view.DoctorView;
import com.meditech.view.ConsultaView;
import com.meditech.view.HistorialView;
import com.meditech.service.NotificacionService;

public class DoctorController {

    private final DoctorView view;
    private final String doctor;
    private final HistorialController historialController;
    private final CitaDAO dao=new CitaDAO();
    private final ConsultaView consultaView;
    private final NotificacionService notificacionService=new NotificacionService();

    public DoctorController(DoctorView view, ConsultaView consultaView, String doctor, HistorialView historialView) {
        this.view = view;
        this.doctor = doctor;
        this.historialController= new HistorialController(historialView);
        this.consultaView = consultaView;
        cargarCitas();
        mostrarCitaHoy();
    }

    private void cargarCitas(){

        view.table.getItems().addAll(
                dao.listarCitasPorDoctor(doctor)
        );
    }

    private void eventos(){

        view.btnAbrirConsulta.setOnAction(event -> {

            Cita citaSeleccionada= view.table.getSelectionModel().getSelectedItem();

            if(citaSeleccionada!=null){

                consultaView.txtExpedienteId.setText(
                        String.valueOf(citaSeleccionada.getExpedienteId())
                );

                historialController.cargarHistorial(
                        citaSeleccionada.getExpedienteId()
                );

            }
        });
    }

    private void mostrarCitaHoy(){

        int total= dao.contarCitasHoy(doctor);

        if(total>0){

            notificacionService.mostrarInfo(
                    "Tienes" +
                            total +
                            " citas pendientes hoy"
            );
        }
    }

}
