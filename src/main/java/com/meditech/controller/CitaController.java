package com.meditech.controller;

import com.meditech.database.CitaDAO;
import com.meditech.model.Cita;
import com.meditech.service.ReplicationService;
import com.meditech.view.CitaView;

import javafx.scene.control.Alert;

public class CitaController {

    private final CitaView view;

    private final CitaDAO dao=new CitaDAO();

    private final ReplicationService replication =  new ReplicationService();

    public CitaController(CitaView view){
        this.view=view;
    }

    private void cargarTabla(){

        view.table.getItems().clear();
        view.table.getItems().addAll(dao.listarCitas());
    }

//    eventos
    private void eventos(){

        view.btnGuardar.setOnAction(e -> {

            try {
                if(view.txtPacienteId.getText().isEmpty()){

                    alerta("Paciente ID requerido");
                    return;
                }

                Cita cita=
                        new Cita(
                                Integer.parseInt(view.txtPacienteId.getText()),
                                view.txtFecha.getText(),
                                view.txtMotivo.getText()
                        );

                replication.replicarCita(cita);

                cargarTabla();
                limpiar();
                info("Cita guardada correctamente");

            }catch(Exception ex){
                alerta("Error al cargar cita");
            }
        });
    }

    private void limpiar(){

        view.txtPacienteId.clear();
        view.txtFecha.clear();
        view.txtMotivo.clear();

    }

    private void alerta(String mensaje){

        Alert alert=
                new Alert (Alert.AlertType.INFORMATION);

        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    private void info(String mensaje){

        Alert alert=
                new Alert (Alert.AlertType.INFORMATION);

        alert.setTitle("Info");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
