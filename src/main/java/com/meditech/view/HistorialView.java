package com.meditech.view;

import com.meditech.model.Consulta;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

public class HistorialView {

    public TableView<Consulta> tabla= new TableView<>();

    public VBox crearVista(){

        TableColumn<Consulta, String> colFecha= new TableColumn<>("Fecha");
        colFecha.setCellValueFactory(
                new javafx.scene.control.cell.PropertyValueFactory<>("fecha")
        );

        TableColumn<Consulta, String> colDiagnostico= new TableColumn<>("Diagnostico");
        colDiagnostico.setCellValueFactory(
                new javafx.scene.control.cell.PropertyValueFactory<>("diagnostico")
        );

        TableColumn<Consulta, String> colTratamiento= new TableColumn<>("Tratamiento");
        colTratamiento.setCellValueFactory(
                new javafx.scene.control.cell.PropertyValueFactory<>("tratamiento")
        );

        tabla.getColumns().addAll(colFecha, colDiagnostico, colTratamiento);

        VBox root = new VBox(
                10,
                new Label(
                        "Historial clínico"
                ),
                tabla
        );
        return root;
    };
}
