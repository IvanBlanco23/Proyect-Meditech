package com.meditech.view;

import com.meditech.model.Cita;

import javafx.scene.control.*;
import javafx.scene.layout.VBox;

public class CitaView {

    public TableView<Cita> table = new TableView<>();

    public TextField txtPacienteId = new TextField();
    public TextField txtFecha = new TextField();
    public TextField txtMotivo = new TextField();
    public TextField txtDoctor = new TextField();

    public Button btnGuardar = new Button("Guardar Cita");
    public Button btnReagendar = new Button("Reagendar Cita");
    public Button btnAusente = new Button("Ausentar cita");

    public VBox crearVista(){

        TableColumn <Cita,String> colId = new TableColumn<>("ID");
        colId.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("id"));

        TableColumn <Cita, String> colPacienteId = new TableColumn<>("Paciente");
        colPacienteId.setCellValueFactory(new  javafx.scene.control.cell.PropertyValueFactory<>("pacienteId"));

        TableColumn <Cita,String> colFecha = new TableColumn<>("Fecha");
        colFecha.setCellValueFactory(new  javafx.scene.control.cell.PropertyValueFactory<>("fecha"));

        TableColumn <Cita,String> colMotivo = new TableColumn<>("Motivo");
        colMotivo.setCellValueFactory(new  javafx.scene.control.cell.PropertyValueFactory<>("motivo"));

        TableColumn <Cita,String> colDoctor = new TableColumn<>("Docotr");
        colDoctor.setCellValueFactory(new  javafx.scene.control.cell.PropertyValueFactory<>("doctor"));

        table.getColumns().addAll(
                colId,
                colPacienteId,
                colFecha,
                colMotivo);

        VBox root = new VBox(
                10,

                new Label("Paceinte Id"),
                txtPacienteId,

                new Label("Fecha"),
                txtFecha,

                new Label("Motivo"),
                txtMotivo,

                new Label("Doctor"),
                txtDoctor,

                btnGuardar,
                btnReagendar,
                btnAusente,

                table
        );

        return root;

    };
}
