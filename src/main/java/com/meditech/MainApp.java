package com.meditech;

import com.meditech.view.*;
import com.meditech.controller.* ;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) {

        LoginView loginView =
                new LoginView();

        new LoginController(
                loginView,
                stage
        );

        Scene scene =
                new Scene(
                        loginView.crearVista(),
                        500,
                        400
                );

        scene.getStylesheets().add(

                getClass()

                        .getResource(
                                "/css/style.css"
                        )

                        .toExternalForm()
        );

        stage.setTitle("MediTech Login");

        stage.setScene(scene);

        stage.show();
    }

    // =====================

    public static void mostrarSistema(
            Stage stage
    ) {

        DashboardView dashboard =
                new DashboardView();

        PacienteView pacienteView =
                new PacienteView();

        new PacienteController(
                pacienteView
        );

        CitaView citaView =
                new CitaView();

        new CitaController(
                citaView
        );

        Tab tabDashboard =
                new Tab(
                        "Inicio",
                        dashboard.crearVista()
                );

        Tab tabPacientes =
                new Tab(
                        "Pacientes",
                        pacienteView.crearVista()
                );

        Tab tabCitas =
                new Tab(
                        "Citas",
                        citaView.crearVista()
                );

        TabPane tabPane =
                new TabPane(

                        tabDashboard,

                        tabPacientes,

                        tabCitas
                );

        Scene scene =
                new Scene(
                        tabPane,
                        1000,
                        700
                );

        scene.getStylesheets().add(

                MainApp.class

                        .getResource(
                                "/css/style.css"
                        )

                        .toExternalForm()
        );

        stage.setTitle("MediTech");

        stage.setScene(scene);
    }

    public static void main(String[] args) {

        launch();
    }
}