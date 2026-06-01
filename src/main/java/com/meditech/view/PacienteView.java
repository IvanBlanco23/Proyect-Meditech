package com.meditech.view;

import com.meditech.model.Paciente;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class PacienteView {

    // ── Campos públicos requeridos por PacienteController ─────────────
    public TableView<Paciente> table        = new TableView<>();
    public TextField           txtNombre    = new TextField();
    public TextField           txtEdad      = new TextField();
    public TextField           txtTelefono  = new TextField();
    public TextField           txtBuscar    = new TextField();
    public Button              btGuardar    = new Button("Guardar");
    public Button              btnActualizar= new Button("Actualizar");
    public Button              btnEliminar  = new Button("Eliminar");
    public Button              btnPDF       = new Button("Exportar PDF");
    public Button              btnExcel     = new Button("Exportar Excel");

    public VBox crearVista() {

        // ── Encabezado ────────────────────────────────────────────────
        Label titulo = new Label("Pacientes");
        titulo.setFont(Font.font("Segoe UI", FontWeight.BOLD, 22));
        titulo.setStyle("-fx-text-fill: white;");
        Label subtitulo = new Label("Registro y gestión de pacientes");
        subtitulo.setStyle("-fx-text-fill: #9ca3af; -fx-font-size: 12px;");
        VBox header = new VBox(3, titulo, subtitulo);

        // ── Barra de búsqueda ─────────────────────────────────────────
        txtBuscar.setPromptText("🔍  Buscar paciente por nombre...");
        txtBuscar.setPrefHeight(38);
        aplicarEstiloCampo(txtBuscar);
        HBox busqueda = new HBox(txtBuscar);
        HBox.setHgrow(txtBuscar, Priority.ALWAYS);

        // ── Tabla ─────────────────────────────────────────────────────
        TableColumn<Paciente, Integer> colId = col("ID",       "id",       70);
        TableColumn<Paciente, String>  colNombre = col("Nombre","nombre",  200);
        TableColumn<Paciente, Integer> colEdad   = col("Edad",  "edad",     80);
        TableColumn<Paciente, String>  colTel    = col("Teléfono","telefono",140);

        table.getColumns().addAll(colId, colNombre, colEdad, colTel);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.setPrefHeight(300);
        table.setStyle("""
            -fx-background-color: #1f2937;
            -fx-border-color: #374151;
            -fx-border-radius: 8;
            -fx-background-radius: 8;
        """);
        VBox.setVgrow(table, Priority.ALWAYS);

        // ── Panel de formulario ───────────────────────────────────────
        VBox form = crearFormulario();

        // ── Layout principal: tabla a la izquierda, form a la derecha ─
        HBox content = new HBox(16,
            wrapCard("Lista de Pacientes", new VBox(10, busqueda, table)),
            form
        );
        HBox.setHgrow(content.getChildren().get(0), Priority.ALWAYS);
        content.setPadding(new Insets(0));
        VBox.setVgrow(content, Priority.ALWAYS);

        VBox root = new VBox(16, header, content);
        root.setPadding(new Insets(24));
        root.setStyle("-fx-background-color: #111827;");
        VBox.setVgrow(root, Priority.ALWAYS);
        return root;
    }

    private VBox crearFormulario() {
        Label formTitle = new Label("Datos del Paciente");
        formTitle.setFont(Font.font("Segoe UI", FontWeight.BOLD, 14));
        formTitle.setStyle("-fx-text-fill: white;");

        // campos
        txtNombre.setPromptText("Nombre completo");
        txtEdad.setPromptText("Edad");
        txtTelefono.setPromptText("Teléfono");
        aplicarEstiloCampo(txtNombre);
        aplicarEstiloCampo(txtEdad);
        aplicarEstiloCampo(txtTelefono);
        txtNombre.setPrefHeight(38);
        txtEdad.setPrefHeight(38);
        txtTelefono.setPrefHeight(38);

        // botones de acción CRUD
        estilizarBoton(btGuardar,     "#2563eb", false);
        estilizarBoton(btnActualizar, "#059669", false);
        estilizarBoton(btnEliminar,   "#dc2626", false);
        btGuardar.setPrefWidth(Double.MAX_VALUE);
        btnActualizar.setPrefWidth(Double.MAX_VALUE);
        btnEliminar.setPrefWidth(Double.MAX_VALUE);

        // botones de exportación
        estilizarBoton(btnPDF,   "#7c3aed", true);
        estilizarBoton(btnExcel, "#065f46", true);
        btnPDF.setPrefWidth(Double.MAX_VALUE);
        btnExcel.setPrefWidth(Double.MAX_VALUE);

        Separator sep = new Separator();
        sep.setStyle("-fx-background-color: #374151;");

        VBox form = new VBox(10,
            formTitle,
            campoConLabel("Nombre",   txtNombre),
            campoConLabel("Edad",     txtEdad),
            campoConLabel("Teléfono", txtTelefono),
            btGuardar, btnActualizar, btnEliminar,
            sep,
            lblSeccion("Exportar"),
            btnPDF, btnExcel
        );
        form.setPadding(new Insets(20));
        form.setMinWidth(220);
        form.setMaxWidth(240);
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
        String style = outline
            ? "-fx-background-color: transparent; -fx-border-color: " + color
              + "; -fx-border-radius: 8; -fx-text-fill: " + color
              + "; -fx-font-size: 13px; -fx-padding: 7 12; -fx-cursor: hand;"
            : "-fx-background-color: " + color
              + "; -fx-text-fill: white; -fx-background-radius: 8;"
              + " -fx-font-size: 13px; -fx-padding: 8 12; -fx-cursor: hand;";
        b.setStyle(style);
    }

    private VBox campoConLabel(String lbl, Control campo) {
        Label l = new Label(lbl);
        l.setStyle("-fx-text-fill: #d1d5db; -fx-font-size: 12px;");
        return new VBox(4, l, campo);
    }

    private Label lblSeccion(String text) {
        Label l = new Label(text.toUpperCase());
        l.setStyle("-fx-text-fill: #6b7280; -fx-font-size: 11px; -fx-font-weight: bold;");
        return l;
    }

    private VBox wrapCard(String title, VBox content) {
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
