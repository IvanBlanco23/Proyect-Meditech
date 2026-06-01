package com.meditech.view;

import com.meditech.model.Cita;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class DoctorView {

    // ── Campos públicos requeridos por DoctorController ───────────────
    public TableView<Cita> table           = new TableView<>();
    public Button          btnAbrirConsulta= new Button("Abrir Consulta");

    public VBox crearVista() {

        // ── Encabezado ────────────────────────────────────────────────
        StackPane avatar = new StackPane();
        Circle c = new Circle(24);
        c.setFill(Color.web("#2563eb"));
        Label initials = new Label("DR");
        initials.setFont(Font.font("Segoe UI", FontWeight.BOLD, 13));
        initials.setStyle("-fx-text-fill: white;");
        avatar.getChildren().addAll(c, initials);

        Label titulo = new Label("Mis Citas del Día");
        titulo.setFont(Font.font("Segoe UI", FontWeight.BOLD, 22));
        titulo.setStyle("-fx-text-fill: white;");
        Label subtitulo = new Label("Citas asignadas y agenda personal");
        subtitulo.setStyle("-fx-text-fill: #9ca3af; -fx-font-size: 12px;");

        HBox headerRow = new HBox(12, avatar, new VBox(3, titulo, subtitulo));
        headerRow.setAlignment(Pos.CENTER_LEFT);

        // ── Leyenda de estados ────────────────────────────────────────
        HBox leyenda = new HBox(16,
            badge("PENDIENTE",  "#d97706"),
            badge("ATENDIDA",   "#059669"),
            badge("AUSENTE",    "#dc2626"),
            badge("REAGENDADA", "#7c3aed")
        );
        leyenda.setAlignment(Pos.CENTER_LEFT);
        leyenda.setPadding(new Insets(0, 0, 8, 0));

        // ── Tabla ─────────────────────────────────────────────────────
        TableColumn<Cita, Integer> colPaciente = col("Paciente", "pacienteId", 100);
        TableColumn<Cita, String>  colFecha    = col("Fecha",    "fecha",      140);
        TableColumn<Cita, String>  colMotivo   = col("Motivo",   "motivo",     200);
        TableColumn<Cita, String>  colEstado   = col("Estado",   "estado",     110);

        table.getColumns().addAll(colPaciente, colFecha, colMotivo, colEstado);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.setStyle("""
            -fx-background-color: #1f2937;
            -fx-border-color: #374151;
            -fx-border-radius: 8;
            -fx-background-radius: 8;
        """);
        VBox.setVgrow(table, Priority.ALWAYS);

        // ── Botón principal ───────────────────────────────────────────
        btnAbrirConsulta.setPrefHeight(44);
        btnAbrirConsulta.setPrefWidth(220);
        btnAbrirConsulta.setFont(Font.font("Segoe UI", FontWeight.BOLD, 14));
        btnAbrirConsulta.setStyle("""
            -fx-background-color: #2563eb;
            -fx-text-fill: white;
            -fx-background-radius: 8;
            -fx-cursor: hand;
        """);

        // Hint de instrucción
        Label hint = new Label("Selecciona una cita y presiona el botón para iniciar la consulta");
        hint.setStyle("-fx-text-fill: #6b7280; -fx-font-size: 12px;");
        hint.setWrapText(true);

        HBox bottomBar = new HBox(16, hint, btnAbrirConsulta);
        bottomBar.setAlignment(Pos.CENTER_LEFT);
        HBox.setHgrow(hint, Priority.ALWAYS);

        // ── Card principal ────────────────────────────────────────────
        VBox tableCard = new VBox(12,
            new Label("") {{ setStyle("-fx-text-fill:#9ca3af; -fx-font-size:12px;"); setVisible(false); }},
            leyenda,
            table
        );
        tableCard.setPadding(new Insets(20));
        tableCard.setStyle("""
            -fx-background-color: #1f2937;
            -fx-background-radius: 12;
            -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.3), 10, 0, 0, 3);
        """);
        VBox.setVgrow(tableCard, Priority.ALWAYS);

        VBox root = new VBox(16, headerRow, tableCard, bottomBar);
        root.setPadding(new Insets(24));
        root.setStyle("-fx-background-color: #111827;");
        VBox.setVgrow(root, Priority.ALWAYS);
        return root;
    }

    // ── Helpers ───────────────────────────────────────────────────────

    private <S,T> TableColumn<S,T> col(String title, String prop, double minWidth) {
        TableColumn<S,T> c = new TableColumn<>(title);
        c.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>(prop));
        c.setMinWidth(minWidth);
        return c;
    }

    private HBox badge(String text, String color) {
        Label dot = new Label("●");
        dot.setStyle("-fx-text-fill: " + color + "; -fx-font-size: 10px;");
        Label lbl = new Label(text);
        lbl.setStyle("-fx-text-fill: #9ca3af; -fx-font-size: 11px;");
        HBox b = new HBox(4, dot, lbl);
        b.setAlignment(Pos.CENTER_LEFT);
        return b;
    }
}
