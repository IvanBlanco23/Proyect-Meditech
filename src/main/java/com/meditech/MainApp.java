package com.meditech;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.sql.SQLException;
import java.util.List;

import com.meditech.database.PacienteDAO;
import com.meditech.model.Paciente;
import com.meditech.database.SQLServerConnection;

import java.sql.SQLClientInfoException;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        PacienteDAO pacienteDAO = new PacienteDAO();

//        Tabla

        TableView<Paciente> table = new TableView<>();

        TableColumn<Paciente, Integer> colId = new TableColumn<>("ID");
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Paciente, String> colNombre = new TableColumn<>("NOMBRE");
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));

        TableColumn<Paciente, Integer> colEdad = new TableColumn<>("EDAD");
        colEdad.setCellValueFactory(new PropertyValueFactory<>("edad"));

        TableColumn<Paciente, String> colTelefono = new TableColumn<>("TELEFONO");
        colTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));

        table.getColumns().addAll(
                colId,
                colNombre,
                colEdad,
                colTelefono
        );

        table.getItems() .addAll(
                pacienteDAO.listarPaciente()
        );

//        Formulario
        Label lb1Nombre = new Label("Nombre:");
        TextField txtNombre = new TextField();

        Label lb1Edad = new Label("Edad:");
        TextField txtEdad = new TextField();

        Label lb1Telefono = new Label("Telefono:");
        TextField txtTelefono = new TextField();

        Button btGuardar = new Button("Guardar");

//        Evento
        btGuardar.setOnAction(e -> {

            try {

                Paciente paciente = new Paciente(
                        txtNombre.getText(),
                        Integer.parseInt(txtEdad.getText()),
                        txtTelefono.getText()
                );

                pacienteDAO.guardarPaciente(paciente);

                table.getItems().clear();
                table.getItems().addAll(pacienteDAO.listarPaciente());

                txtNombre.clear();
                txtEdad.clear();
                txtTelefono.clear();

            }catch (Exception ex){

                System.out.println("ERROR");
                ex.printStackTrace();

            }
        });

//        Layout
        VBox root = new VBox(
                10,
                lb1Nombre,
                txtNombre,

                lb1Edad,
                txtEdad,

                lb1Telefono,
                txtTelefono,

                btGuardar,
                table
        );

        Scene scene = new Scene(root, 700, 400);

        stage.setTitle("Meditech");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
