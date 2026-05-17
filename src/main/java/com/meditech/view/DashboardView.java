package com.meditech.view;

import com.meditech.database.*;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class DashboardView {

    public VBox crearVista(){

        PacienteDAO  pacienteDAO = new PacienteDAO();

        CitaDAO citaDAO = new CitaDAO();

        Label titulo= new Label("Meditech");

        titulo.setStyle("""
                -fx-font-size: 32px;
                -fx-font-weight: bold;
                -fx-text-fill: #2563eb;
                """);

        Label subtitulo= new Label("Sistema clinico MediTech");

        subtitulo.setStyle("""
                -fx-font-size: 18px;
                -fx-font-fill: 555;
        """);

        VBox cardPacientes = crearCard(
                "Pacientes",

                String.valueOf(pacienteDAO.contarPacientes())
        );

        VBox cardCitas =
                crearCard(
                        "Citas",

                        String.valueOf(
                                citaDAO.contarCitas()
                        )
                );

        HBox estadisticas = new HBox(
                20,
                cardPacientes,
                cardCitas
        );

        estadisticas.setAlignment(Pos.CENTER);

        VBox root=new VBox(
                30,
                titulo,
                subtitulo,
                estadisticas
        );

        root.setAlignment(Pos.CENTER);

        root.setPrefHeight(600);

        return root;
    }

    private VBox crearCard(String titulo, String valor){

        Label lblTitulo = new Label(titulo);

        lblTitulo.setStyle("""
                -fx-font-size: 18px;
                -fx-font-weight: bold;
                """);

        Label lblValor = new Label(valor);

        lblValor.setStyle("""
                -fx-font-size: 30px;
                -fx-font-fill: #2563eb;
                -fx-font-weight: bold;
                """);

        VBox card = new VBox(
                10,
                lblTitulo,
                lblValor
        );
        card.setAlignment(Pos.CENTER);

        card.setStyle("""
                -fx-background-color: white;
                -fx-padding: 25;
                -fx-background-radius: 12;
                -fx-border-radius: 12;
                
                -fx-effect:
                     dropShadow(
                     gaussian,
                     rgba(0,0,0,0.15),
                     10,
                     0,
                     0,
                     4
                     );
                """);
        return card;
    }
}
