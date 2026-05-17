package com.meditech.view;

import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import org.checkerframework.common.subtyping.qual.Bottom;

public class ConsultaView {

    public TextField txtExpedienteId= new TextField();
    public TextArea txtDiagnostico= new TextArea();
    public TextArea txtTratamiento= new TextArea();
    public TextArea txtObservaciones= new TextArea();
    public DatePicker dpFecha = new DatePicker();
    public Button btnGuardar= new Button("Guardar consulta");

    public VBox crearVista(){

        txtExpedienteId.setPromptText("ID Expediente");

        txtDiagnostico.setPromptText("Diagnostico");

        txtTratamiento.setPromptText("Tratamiento");

        txtObservaciones.setPromptText("Observaciones");

        VBox root = new VBox(
                10,
                new Label("consulta medica"),
                txtExpedienteId,

                new Label("Diagnostico"),
                txtDiagnostico,

                new Label("Observaciones"),
                txtObservaciones,

                new  Button("Fecha"),
                dpFecha,
                btnGuardar
        );
        return root;
    };
}
