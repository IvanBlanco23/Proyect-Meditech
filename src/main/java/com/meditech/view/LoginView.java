package com.meditech.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class LoginView {

    public TextField     txtUsuario = new TextField();
    public PasswordField txtPassword = new PasswordField();
    public Button        btnLogin    = new Button("Iniciar Sesión");

    public VBox crearVista() {

        // ── Icono / logo ──────────────────────────────────────────────
        StackPane logoCircle = new StackPane();
        Circle circle = new Circle(40);
        circle.setFill(Color.web("#2563eb"));
        Label logoLetter = new Label("M");
        logoLetter.setFont(Font.font("Segoe UI", FontWeight.BOLD, 36));
        logoLetter.setStyle("-fx-text-fill: white;");
        logoCircle.getChildren().addAll(circle, logoLetter);

        // ── Título ────────────────────────────────────────────────────
        Label titulo = new Label("MediTech");
        titulo.setFont(Font.font("Segoe UI", FontWeight.BOLD, 28));
        titulo.setStyle("-fx-text-fill: #2563eb;");

        Label subtitulo = new Label("Sistema de Gestión Médica");
        subtitulo.setStyle("-fx-text-fill: #9ca3af; -fx-font-size: 13px;");

        VBox header = new VBox(8, logoCircle, titulo, subtitulo);
        header.setAlignment(Pos.CENTER);

        // ── Campos ────────────────────────────────────────────────────
        Label lblUsuario = new Label("Usuario");
        lblUsuario.setStyle("-fx-text-fill: #d1d5db; -fx-font-size: 13px; -fx-font-weight: bold;");
        txtUsuario.setPromptText("Ingresa tu usuario");
        txtUsuario.setPrefHeight(42);
        txtUsuario.setStyle("""
            -fx-background-color: #374151;
            -fx-text-fill: white;
            -fx-prompt-text-fill: #6b7280;
            -fx-background-radius: 8;
            -fx-border-color: #4b5563;
            -fx-border-radius: 8;
            -fx-padding: 0 12;
            -fx-font-size: 14px;
        """);

        Label lblPass = new Label("Contraseña");
        lblPass.setStyle("-fx-text-fill: #d1d5db; -fx-font-size: 13px; -fx-font-weight: bold;");
        txtPassword.setPromptText("Ingresa tu contraseña");
        txtPassword.setPrefHeight(42);
        txtPassword.setStyle("""
            -fx-background-color: #374151;
            -fx-text-fill: white;
            -fx-prompt-text-fill: #6b7280;
            -fx-background-radius: 8;
            -fx-border-color: #4b5563;
            -fx-border-radius: 8;
            -fx-padding: 0 12;
            -fx-font-size: 14px;
        """);

        // ── Botón ─────────────────────────────────────────────────────
        btnLogin.setPrefHeight(44);
        btnLogin.setPrefWidth(Double.MAX_VALUE);
        btnLogin.setFont(Font.font("Segoe UI", FontWeight.BOLD, 15));
        btnLogin.setStyle("""
            -fx-background-color: #2563eb;
            -fx-text-fill: white;
            -fx-background-radius: 8;
            -fx-cursor: hand;
        """);
        btnLogin.setOnMouseEntered(e -> btnLogin.setStyle("""
            -fx-background-color: #1d4ed8;
            -fx-text-fill: white;
            -fx-background-radius: 8;
            -fx-cursor: hand;
        """));
        btnLogin.setOnMouseExited(e -> btnLogin.setStyle("""
            -fx-background-color: #2563eb;
            -fx-text-fill: white;
            -fx-background-radius: 8;
            -fx-cursor: hand;
        """));

        // ── Card ──────────────────────────────────────────────────────
        VBox card = new VBox(14,
            header,
            new Separator(),
            lblUsuario, txtUsuario,
            lblPass,    txtPassword,
            btnLogin
        );
        card.setPadding(new Insets(36, 40, 36, 40));
        card.setAlignment(Pos.TOP_LEFT);
        card.setStyle("""
            -fx-background-color: #1f2937;
            -fx-background-radius: 16;
            -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.5), 24, 0, 0, 8);
        """);
        card.setMaxWidth(380);
        card.setMinWidth(340);
        VBox.setMargin(header, new Insets(0, 0, 8, 0));
        VBox.setMargin(btnLogin, new Insets(6, 0, 0, 0));

        // ── Root ──────────────────────────────────────────────────────
        VBox root = new VBox(card);
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-background-color: #111827;");
        VBox.setVgrow(root, Priority.ALWAYS);

        return root;
    }
}
