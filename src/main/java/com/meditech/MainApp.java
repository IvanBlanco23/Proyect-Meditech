package com.meditech;

import com.meditech.view.*;
import com.meditech.controller.* ;
import com.meditech.model.Usuario;

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
                                "/css/dark-theme.css"
                        )

                        .toExternalForm()
        );

        stage.setTitle("MediTech Login");

        stage.setScene(scene);

        stage.show();
    }

    // =====================

    public static <usuario> void mostrarSistema(
            Stage stage,

//          Para contraseña con loggin en base
            Usuario usuario
    ) {

        DashboardView dashboard =
                new DashboardView();

        new DashboardController(
                dashboard
        );

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

        NotificacionView notificacionView=
                new NotificacionView();

        new NotificacionController(
                notificacionView
        );

//        si está en gris, es registro con user con database
//        ConsultaView consultaView=
//                new ConsultaView();
//
//        new ConsultaController(
//                consultaView,
//                usuario
//        );

//        DoctorView doctorView=
//                new DoctorView();

//        new DoctorController(
//                doctorView,
//                consultaView
//        );
//

        Tab tabDashboard =
                new Tab(
                        "Dashboard",
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

        Tab tabNotificaciones=
                new Tab(
                        "Notificaciones",

                        notificacionView.crearVista()
                );

//        si esta en gris, es registro con user de Database
//        Tab tabConsulta = new Tab(
//                "Consulta",
//                consultaView.crearVista()
//        );


        TabPane tabPane =
                new TabPane(
//
//                        tabDashboard,
//
//                        tabPacientes,
//
//                        tabCitas,
//
//                        tabConsulta

                );

        String rol = usuario.getRol();

        if (rol.equals("ADMIN")){

            tabPane.getTabs().addAll(
                    tabDashboard,
                    tabPacientes,
                    tabCitas);

        } else if (rol.equals("RECEPCION")) {

            tabPane.getTabs().addAll(
                    tabPacientes,
                    tabCitas);

        } else if (rol.equals("DOCTOR")) {

        DoctorView doctorView=new DoctorView();
        HistorialView historialView=new HistorialView();

        ConsultaView consultaView=
                new ConsultaView();

        new ConsultaController(
                consultaView,
                usuario
        );

        new DoctorController(
                doctorView,
                consultaView,
                usuario.getUsername(),
                historialView
        );

        Tab tabDoctor = new Tab(
                "Mis citas",
                doctorView.crearVista()
        );

        Tab tabConsulta = new Tab("Consulta", consultaView.crearVista());

        tabPane.getTabs().addAll(tabDoctor,tabConsulta);

        tabPane.getTabs().add(tabNotificaciones);

        }

        Scene scene =
                new Scene(
                        tabPane,
                        1000,
                        700
                );

        scene.getStylesheets().add(

                MainApp.class

                        .getResource(
                                "/css/dark-theme.css"
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