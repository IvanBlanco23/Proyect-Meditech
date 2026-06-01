package com.meditech.view;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class ConsultaView {

    // ── Campos públicos requeridos por ConsultaController ─────────────
    public TextField txtExpedienteId = new TextField();
    public TextArea  txtDiagnostico  = new TextArea();
    public TextArea  txtTratamiento  = new TextArea();
    public TextArea  txtObservaciones= new TextArea();
    public DatePicker dpFecha        = new DatePicker();
    public Button    btnGuardar      = new Button("Guardar Consulta");
    public Button    btnreceta       = new Button("Generar Receta PDF");
    public TextArea  txtMedicamentos = new TextArea();

    public VBox crearVista() {

        // ── Encabezado ────────────────────────────────────────────────
        Label titulo = new Label("Consulta Médica");
        titulo.setFont(Font.font("Segoe UI", FontWeight.BOLD, 22));
        titulo.setStyle("-fx-text-fill: white;");
        Label subtitulo = new Label("Registro de diagnóstico y tratamiento");
        subtitulo.setStyle("-fx-text-fill: #9ca3af; -fx-font-size: 12px;");

        // ── Configurar campos ─────────────────────────────────────────
        txtExpedienteId.setPromptText("Ingresa el ID del expediente");
        txtExpedienteId.setPrefHeight(38);
        aplicarEstiloCampo(txtExpedienteId);

        dpFecha.setPromptText("Selecciona la fecha");
        dpFecha.setPrefHeight(38);
        dpFecha.setStyle("""
            -fx-background-color: #374151;
            -fx-background-radius: 8;
        """);

        configurarTextArea(txtDiagnostico, "Ingresa el diagnóstico del paciente...", 80);
        configurarTextArea(txtTratamiento, "Describe el tratamiento indicado...", 80);
        configurarTextArea(txtObservaciones, "Observaciones adicionales...", 70);
        configurarTextArea(txtMedicamentos, "Lista de medicamentos recetados...", 70);

        // ── Botones ───────────────────────────────────────────────────
        btnGuardar.setPrefWidth(Double.MAX_VALUE);
        btnGuardar.setPrefHeight(42);
        btnGuardar.setFont(Font.font("Segoe UI", FontWeight.BOLD, 14));
        estilizarBoton(btnGuardar, "#2563eb", false);

        btnreceta.setPrefWidth(Double.MAX_VALUE);
        btnreceta.setPrefHeight(42);
        estilizarBoton(btnreceta, "#7c3aed", true);

        // ── Sección izquierda (info básica) ───────────────────────────
        VBox leftPanel = new VBox(12,
            seccionTitulo("📋 Información Básica"),
            campoConLabel("ID Expediente", txtExpedienteId),
            campoConLabel("Fecha de Consulta", dpFecha),
            campoConLabel("Diagnóstico", txtDiagnostico)
        );
        leftPanel.setPadding(new Insets(20));
        leftPanel.setStyle(cardStyle());
        VBox.setVgrow(leftPanel, Priority.ALWAYS);

        // ── Sección derecha (tratamiento) ─────────────────────────────
        VBox rightPanel = new VBox(12,
            seccionTitulo("💊 Tratamiento"),
            campoConLabel("Tratamiento",   txtTratamiento),
            campoConLabel("Medicamentos",  txtMedicamentos),
            campoConLabel("Observaciones", txtObservaciones)
        );
        rightPanel.setPadding(new Insets(20));
        rightPanel.setStyle(cardStyle());
        VBox.setVgrow(rightPanel, Priority.ALWAYS);

        HBox panels = new HBox(16, leftPanel, rightPanel);
        HBox.setHgrow(leftPanel, Priority.ALWAYS);
        HBox.setHgrow(rightPanel, Priority.ALWAYS);
        VBox.setVgrow(panels, Priority.ALWAYS);

        // ── Fila de acciones ──────────────────────────────────────────
        HBox acciones = new HBox(12, btnGuardar, btnreceta);
        HBox.setHgrow(btnGuardar, Priority.ALWAYS);
        HBox.setHgrow(btnreceta,  Priority.ALWAYS);

        VBox root = new VBox(16,
            new VBox(3, titulo, subtitulo),
            panels,
            acciones
        );
        root.setPadding(new Insets(24));
        root.setStyle("-fx-background-color: #111827;");
        return root;
    }

    // ── Helpers ───────────────────────────────────────────────────────

    private void aplicarEstiloCampo(Control c) {
        c.setStyle("""
            -fx-background-color: #374151;
            -fx-text-fill: white;
            -fx-prompt-text-fill: #6b7280;
            -fx-background-radius: 8;
            -fx-border-color: #4b5563;
            -fx-border-radius: 8;
            -fx-padding: 0 10;
            -fx-font-size: 13px;
        """);
    }

    private void configurarTextArea(TextArea ta, String prompt, double height) {
        ta.setPromptText(prompt);
        ta.setPrefHeight(height);
        ta.setWrapText(true);
        ta.setStyle("""
            -fx-background-color: #374151;
            -fx-text-fill: white;
            -fx-prompt-text-fill: #6b7280;
            -fx-background-radius: 8;
            -fx-border-color: #4b5563;
            -fx-border-radius: 8;
            -fx-font-size: 13px;
            -fx-padding: 8;
        """);
    }

    private void estilizarBoton(Button b, String color, boolean outline) {
        b.setStyle(outline
            ? "-fx-background-color: transparent; -fx-border-color: " + color
              + "; -fx-border-radius: 8; -fx-text-fill: " + color
              + "; -fx-font-size: 13px; -fx-cursor: hand;"
            : "-fx-background-color: " + color
              + "; -fx-text-fill: white; -fx-background-radius: 8;"
              + " -fx-font-size: 13px; -fx-cursor: hand;");
    }

    private Label seccionTitulo(String text) {
        Label l = new Label(text);
        l.setFont(Font.font("Segoe UI", FontWeight.BOLD, 14));
        l.setStyle("-fx-text-fill: white;");
        return l;
    }

    private VBox campoConLabel(String lbl, javafx.scene.Node campo) {
        Label l = new Label(lbl);
        l.setStyle("-fx-text-fill: #d1d5db; -fx-font-size: 12px;");
        return new VBox(4, l, campo);
    }

    private String cardStyle() {
        return """
            -fx-background-color: #1f2937;
            -fx-background-radius: 12;
            -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.3), 10, 0, 0, 3);
        """;
    }
}
