package com.meditech.service;

import javafx.scene.control.Alert;

public class NotificacionService {

    public void mostrarInfo(String mensaje){

        Alert alert=new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle("Notificacion");

        alert.setHeaderText(null);

        alert.setContentText(mensaje);

        alert.showAndWait();
    }
}
