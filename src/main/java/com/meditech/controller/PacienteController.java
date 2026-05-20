package com.meditech.controller;

import com.meditech.database.PacienteDAO;
import com.meditech.model.Paciente;
import com.meditech.service.ReplicationService;
import com.meditech.view.PacienteView;
import com.meditech.util.PDFExporter;
import com.meditech.service.ExcelService;
import com.meditech.controller.NotificacionController;

import javafx.collections.*;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;

import java.util.Locale;

public class PacienteController {

    private final PacienteView view;

    private final PacienteDAO  pacienteDAO = new PacienteDAO();
    private final ReplicationService replication = new ReplicationService();
    private final ExcelService excel = new ExcelService();
    private NotificacionController notificacionController;

    private Paciente pacienteSeleccionado;

    public PacienteController(PacienteView view) {
        this.view = view;

        cargarTabla();

        eventos();

    }

    private ObservableList<Paciente> pacienteObservable;
    private void cargarTabla(){

        pacienteObservable = FXCollections.observableArrayList(pacienteDAO.listarPacientes());

        FilteredList<Paciente> filtro= new FilteredList<>(
                pacienteObservable,
                p -> true
        );

        view.txtBuscar.textProperty().addListener((obs, oldValue, newValue) -> {
            filtro.setPredicate(paciente -> {
                if (
                        newValue == null || newValue.isEmpty()
                ) {
                    return true;
                }

                String texto = newValue.toLowerCase();

                return pacienteSeleccionado.getNombre().toLowerCase().contains(texto);

            });
        });

        view.table.setItems(filtro);

        view.table.getItems().clear();

    }

    ObservableList<Paciente> pacientes = FXCollections.observableArrayList();
    FilteredList<Paciente> fil=new FilteredList<>(pacientes, p -> true);

    private void eventos(){

//        ============

        view.btGuardar.setOnAction(event -> {

            try {
                if(view.txtNombre.getText().isEmpty()){

                    mostrarAlerta(
                            "Campo nombre vacío"
                    );

                    return;
                }

                Paciente paciente = new Paciente(
                        view.txtNombre.getText(),
                        Integer.parseInt(view.txtEdad.getText()),
                        view.txtTelefono.getText()
                );

                notificacionController.agregar(
                        "Nuevo paciente registrado" +
                               view.txtNombre.getText()
                );

                replication.replicarPaciente(paciente);

                cargarTabla();

                limpiar();

                mostrarInfo(
                        "Paciente guadado"
                );
            }catch (Exception e){
                mostrarAlerta("Error al guardar paciente");
            }
        });

//        =======

        view.table.setOnMouseClicked(event -> {
            pacienteSeleccionado = view.table.getSelectionModel().getSelectedItem();

            if(pacienteSeleccionado != null){

                view.txtNombre.setText(pacienteSeleccionado.getNombre());

                view.txtEdad.setText(String.valueOf(pacienteSeleccionado.getEdad()));

                view.txtTelefono.setText(pacienteSeleccionado.getTelefono());

            }

        });

//        ======

        view.btnPDF.setOnAction(event -> {

            PDFExporter.exportarPacientes(
                    pacienteDAO.listarPacientes()
            );

            mostrarAlerta("PDF exportado correctamente");
        });

//        =======

        view.txtBuscar.textProperty().addListener((obs, oldValue, newValue) -> {
            fil.setPredicate(paciente -> {

                if(newValue == null || newValue.isEmpty()){

                    return true;

                }

                String texto = newValue.toLowerCase();

                return pacienteSeleccionado.getNombre().toLowerCase().contains(texto);

            });
        });

        SortedList<Paciente> sortedData = new SortedList<>(fil);

        sortedData.comparatorProperty().bind(view.table.comparatorProperty());

        view.table.setItems(sortedData);

//        ==========

        view.btnExcel.setOnAction(event -> {
            excel.exportarPacientes(pacienteDAO.listarPacientes());
            mostrarInfo("Excel exportado correctamente");
        });
    }

    private void limpiar(){
        view.txtNombre.clear();
        view.txtEdad.clear();
        view.txtTelefono.clear();
    }

    private void mostrarInfo(String mensaje){

        javafx.scene.control.Alert alert =
                new javafx.scene.control.Alert(
                        javafx.scene.control.Alert.AlertType.INFORMATION
                );

        alert.setTitle("Información");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    private void mostrarAlerta(String mensaje){

        javafx.scene.control.Alert alert=
                new javafx.scene.control.Alert(
                        javafx.scene.control.Alert.AlertType.ERROR
                );

        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
