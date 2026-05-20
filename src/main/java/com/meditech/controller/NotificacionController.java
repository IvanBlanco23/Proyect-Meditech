package com.meditech.controller;

import com.meditech.model.Notificacion;
import com.meditech.view.NotificacionView;

public class NotificacionController {

    private final NotificacionView view;

    public NotificacionController(NotificacionView view) {
        this.view = view;
    }

    public void agregar(String mensaje){

        view.lista.getItems().add(new Notificacion(mensaje));
    }
}
