package com.meditech.view;

import com.meditech.model.Cita;

import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import org.checkerframework.common.subtyping.qual.Bottom;

public class DoctorView {

    public TableView<Cita> table =  new TableView<>();

    public Button btnAbrirConsulta=new  Button("Abrir Consulta");

    public VBox crearVista(){

        TableColumn<Cita,Integer> colPaciente = new TableColumn<>("Paciente");
        colPaciente.setCellValueFactory(
                new javafx.scene.control.cell.PropertyValueFactory<>(
                        "pacienteId"
                )
        );

        TableColumn<Cita,String> colFecha = new TableColumn<>("Fecha");
        colFecha.setCellValueFactory(
                new javafx.scene.control.cell.PropertyValueFactory<>("fecha")
        );

        TableColumn<Cita,String> colMotivo = new TableColumn<>("Motivo");
        colMotivo.setCellValueFactory(
                new javafx.scene.control.cell.PropertyValueFactory<>("motivo")
        );

        table.getColumns().addAll(
                colPaciente,
                colFecha,
                colMotivo
        );

        VBox root = new VBox(
                10,
                new Label(
                        "Mis citas"
                ),
                table,

                btnAbrirConsulta
        );
        return root;
    }
}
