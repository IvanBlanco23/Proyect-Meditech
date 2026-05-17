package com.meditech.view;

import com.meditech.model.Paciente;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

public class PacienteView {

    public TableView<Paciente> table = new TableView<>();

    public TextField txtNombre = new TextField();
    public TextField txtEdad = new TextField();
    public TextField txtTelefono = new TextField();
    public TextField txtBuscar = new TextField();

    public Button btGuardar = new Button("Guardar");
    public Button btnActualizar = new Button("Actualizar");
    public Button btnEliminar = new Button("Eliminar");
    public Button btnPDF= new Button("Exportar PDF");

    public VBox crearVista (){

        TableColumn<Paciente, Integer> colId = new TableColumn<>("ID");

        colId.setCellValueFactory(
                new javafx.scene.control.cell.PropertyValueFactory<>("id")
        );

        TableColumn<Paciente, String> colNombre = new TableColumn<>("Nombre");

        colNombre.setCellValueFactory(
                new javafx.scene.control.cell.PropertyValueFactory<>("nombre")
        );

        TableColumn<Paciente, String> colEdad = new TableColumn<>("Edad");

        colEdad.setCellValueFactory(
                new javafx.scene.control.cell.PropertyValueFactory<>("edad")
        );

        TableColumn<Paciente, String> colTelefono = new TableColumn<>("Telefono");

        colTelefono.setCellValueFactory(
                new  javafx.scene.control.cell.PropertyValueFactory<>("telefono")
        );

        table.getColumns().addAll(
                colId,
                colNombre,
                colEdad,
                colTelefono
        );

        VBox root = new VBox(
                10,

                new Label("Nombre"),
                txtNombre,

                new Label("Edad"),
                txtEdad,

                new Label("Telefono"),
                txtTelefono,

                btGuardar,
                btnActualizar,
                btnEliminar,
                btnPDF,

                new Label("Buscar Paciente"),
                txtBuscar,

                table
        );

        return root;
    }
}
