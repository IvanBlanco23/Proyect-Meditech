package com.meditech.controller;

import com.meditech.database.ConsultaDAO;
import com.meditech.database.UsuarioDAO;
import com.meditech.model.Consulta;
import com.meditech.model.Usuario;
import com.meditech.view.ConsultaView;

import javafx.scene.control.Alert;

public class ConsultaController {

    private final ConsultaView view;
    private final Usuario doctor;
    private final ConsultaDAO dao= new ConsultaDAO();

//    Mostrar para el registro: User de Database
    public ConsultaController(ConsultaView view, Usuario doctor) {
        this.view = view;
        this.doctor = doctor;

        eventos();
    }

    private void eventos(){
        view.btnGuardar.setOnAction(e ->{

            try {
                Consulta consulta = new Consulta(
                        Integer.parseInt(view.txtExpedienteId.getText()),

                        doctor.getIdUsuario(),

                        view.txtDiagnostico.getText(),

                        view.txtTratamiento.getText(),

                        view.txtObservaciones.getText(),

                        view.dpFecha.getValue().toString()
                );

                dao.guardar(consulta);

                mostrarInfo("Consulta guardada");
                limpiar();
            }catch (Exception ex){
                mostrarError("Error al guardar consulta");

                ex.printStackTrace();
            }
        });
    }

    private  void limpiar(){
        view.txtExpedienteId.clear();

        view.txtDiagnostico.clear();

        view.txtTratamiento.clear();

        view.txtObservaciones.clear();

        view.dpFecha.setValue(null);
    }

    private void mostrarInfo(String mensaje){

        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setContentText(mensaje);

        alert.showAndWait();
    }

    private void mostrarError(String mensaje){

        Alert alert = new Alert(Alert.AlertType.ERROR);

        alert.setContentText(mensaje);

        alert.showAndWait();
    }
}
