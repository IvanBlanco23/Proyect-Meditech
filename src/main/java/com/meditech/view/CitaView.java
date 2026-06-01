package com.meditech.view;

import com.meditech.model.Cita;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class CitaView {

    // ── Campos públicos requeridos por CitaController ─────────────────
    public TableView<Cita> table          = new TableView<>();
    public TextField       txtPacienteId  = new TextField();
    public TextField       txtFecha       = new TextField();
    public TextField       txtMotivo      = new TextField();
    public TextField       txtDoctor      = new TextField();
    public Button          btnGuardar     = new Button("Guardar Cita");
    public Button          btnReagendar   = new Button("Reagendar Cita");
    public Button          btnAusente     = new Button("Marcar Ausente");

    public VBox crearVista() {

        // ── Encabezado ────────────────────────────────────────────────
        Label titulo = new Label("Citas Médicas");
        titulo.setFont(Font.font("Segoe UI", FontWeight.BOLD, 22));
        titulo.setStyle("-fx-text-fill: white;");
        Label subtitulo = new Label("Gestión de citas y agenda");
        subtitulo.setStyle("-fx-text-fill: #9ca3af; -fx-font-size: 12px;");
        VBox header = new VBox(3, titulo, subtitulo);

        // ── Tabla ─────────────────────────────────────────────────────
        TableColumn<Cita, Integer> colId       = col("ID",       "id",         60);
        TableColumn<Cita, Integer> colPaciente = col("Paciente", "pacienteId", 100);
        TableColumn<Cita, String>  colFecha    = col("Fecha",    "fecha",      130);
        TableColumn<Cita, String>  colMotivo   = col("Motivo",   "motivo",     180);
        TableColumn<Cita, String>  colDoctor   = col("Doctor",   "doctor",     140);
        TableColumn<Cita, String>  colEstado   = col("Estado",   "estado",     110);

        table.getColumns().addAll(colId, colPaciente, colFecha, colMotivo, colDoctor, colEstado);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.setPrefHeight(320);
        table.setStyle("""
            -fx-background-color: #1f2937;
            -fx-border-color: #374151;
            -fx-border-radius: 8;
            -fx-background-radius: 8;
        """);
        VBox.setVgrow(table, Priority.ALWAYS);

        // ── Formulario ────────────────────────────────────────────────
        VBox form = crearFormulario();

        // ── Layout ────────────────────────────────────────────────────
        HBox content = new HBox(16,
            wrapCard("Agenda de Citas", table),
            form
        );
        HBox.setHgrow(content.getChildren().get(0), Priority.ALWAYS);
        VBox.setVgrow(content, Priority.ALWAYS);

        VBox root = new VBox(16, header, content);
        root.setPadding(new Insets(24));
        root.setStyle("-fx-background-color: #111827;");
        VBox.setVgrow(root, Priority.ALWAYS);
        return root;
    }

    private VBox crearFormulario() {

        // configurar campos
        txtPacienteId.setPromptText("ID del paciente");
        txtFecha.setPromptText("YYYY-MM-DD HH:MM");
        txtMotivo.setPromptText("Motivo de consulta");
        txtDoctor.setPromptText("Nombre del doctor");

        for (TextField tf : new TextField[]{txtPacienteId, txtFecha, txtMotivo, txtDoctor}) {
            tf.setPrefHeight(38);
            aplicarEstiloCampo(tf);
        }

        // botones
        estilizarBoton(btnGuardar,   "#2563eb", false);
        estilizarBoton(btnReagendar, "#d97706", false);
        estilizarBoton(btnAusente,   "#dc2626", true);
        for (Button b : new Button[]{btnGuardar, btnReagendar, btnAusente}) {
            b.setPrefWidth(Double.MAX_VALUE);
        }

        Label formTitle = new Label("Nueva Cita");
        formTitle.setFont(Font.font("Segoe UI", FontWeight.BOLD, 14));
        formTitle.setStyle("-fx-text-fill: white;");

        // hint de fecha
        Label hintFecha = new Label("Formato: 2025-06-15 09:30");
        hintFecha.setStyle("-fx-text-fill: #6b7280; -fx-font-size: 11px;");

        VBox form = new VBox(10,
            formTitle,
            campoConLabel("ID Paciente", txtPacienteId),
            campoConLabel("Fecha / Hora", new VBox(3, txtFecha, hintFecha)),
            campoConLabel("Motivo",  txtMotivo),
            campoConLabel("Doctor",  txtDoctor),
            new Separator(),
            lblSeccion("Acciones"),
            btnGuardar,
            btnReagendar,
            btnAusente
        );
        form.setPadding(new Insets(20));
        form.setMinWidth(220);
        form.setMaxWidth(250);
        form.setStyle("""
            -fx-background-color: #1f2937;
            -fx-background-radius: 12;
            -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.3), 10, 0, 0, 3);
        """);
        return form;
    }

    // ── Helpers ───────────────────────────────────────────────────────

    private <S,T> TableColumn<S,T> col(String title, String prop, double minWidth) {
        TableColumn<S,T> c = new TableColumn<>(title);
        c.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>(prop));
        c.setMinWidth(minWidth);
        return c;
    }

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

    private void estilizarBoton(Button b, String color, boolean outline) {
        b.setStyle(outline
            ? "-fx-background-color: transparent; -fx-border-color: " + color
              + "; -fx-border-radius: 8; -fx-text-fill: " + color
              + "; -fx-font-size: 13px; -fx-padding: 8 12; -fx-cursor: hand;"
            : "-fx-background-color: " + color
              + "; -fx-text-fill: white; -fx-background-radius: 8;"
              + " -fx-font-size: 13px; -fx-padding: 8 12; -fx-cursor: hand;");
    }

    private VBox campoConLabel(String lbl, javafx.scene.Node campo) {
        Label l = new Label(lbl);
        l.setStyle("-fx-text-fill: #d1d5db; -fx-font-size: 12px;");
        return new VBox(4, l, campo);
    }

    private Label lblSeccion(String text) {
        Label l = new Label(text.toUpperCase());
        l.setStyle("-fx-text-fill: #6b7280; -fx-font-size: 11px; -fx-font-weight: bold;");
        return l;
    }

    private VBox wrapCard(String title, javafx.scene.Node content) {
        Label t = new Label(title);
        t.setFont(Font.font("Segoe UI", FontWeight.BOLD, 14));
        t.setStyle("-fx-text-fill: white;");
        VBox card = new VBox(12, t, content);
        card.setPadding(new Insets(20));
        card.setStyle("""
            -fx-background-color: #1f2937;
            -fx-background-radius: 12;
            -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.3), 10, 0, 0, 3);
        """);
        VBox.setVgrow(content, Priority.ALWAYS);
        HBox.setHgrow(card, Priority.ALWAYS);
        return card;
    }
}
