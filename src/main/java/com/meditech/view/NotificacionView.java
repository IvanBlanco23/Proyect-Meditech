package com.meditech.view;

import com.meditech.model.Notificacion;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

public class NotificacionView {

    public ListView<Notificacion> lista=new ListView<>();

    public VBox crearVista(){

        VBox root=new VBox(
                10,
                new Label(
                        "🔔 Notificaciones"
                ),
                lista
        );

        return root;

    }
}
