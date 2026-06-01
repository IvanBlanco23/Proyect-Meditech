package com.meditech.view;

import com.meditech.model.Consulta;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class HistorialView {

    // ── Campo público requerido por DoctorController ──────────────────
    public TableView<Consulta> tabla = new TableView<>();

    public VBox crearVista() {

        // ── Encabezado ────────────────────────────────────────────────
        Label titulo = new Label("📁 Historial Clínico");
        titulo.setFont(Font.font("Segoe UI", FontWeight.BOLD, 22));
        titulo.setStyle("-fx-text-fill: white;");
        Label subtitulo = new Label("Consultas médicas anteriores del paciente");
        subtitulo.setStyle("-fx-text-fill: #9ca3af; -fx-font-size: 12px;");

        // ── Tabla ─────────────────────────────────────────────────────
        TableColumn<Consulta, String> colFecha        = col("Fecha",       "fecha",        120);
        TableColumn<Consulta, String> colDiagnostico  = col("Diagnóstico", "diagnostico",  200);
        TableColumn<Consulta, String> colTratamiento  = col("Tratamiento", "tratamiento",  200);
        TableColumn<Consulta, String> colMedicamentos = col("Medicamentos","medicamentos", 180);

        tabla.getColumns().addAll(colFecha, colDiagnostico, colTratamiento, colMedicamentos);
        tabla.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tabla.setStyle("""
            -fx-background-color: #1f2937;
            -fx-border-color: #374151;
            -fx-border-radius: 8;
            -fx-background-radius: 8;
        """);
        tabla.setPlaceholder(new Label("No hay consultas registradas para este paciente") {{
            setStyle("-fx-text-fill: #6b7280; -fx-font-size: 13px;");
        }});
        VBox.setVgrow(tabla, Priority.ALWAYS);

        // ── Tip ───────────────────────────────────────────────────────
        Label tip = new Label("ℹ️  El historial se carga automáticamente al seleccionar una cita en la vista del Doctor");
        tip.setWrapText(true);
        tip.setStyle("""
            -fx-text-fill: #6b7280;
            -fx-font-size: 12px;
            -fx-background-color: #1f2937;
            -fx-background-radius: 8;
            -fx-border-color: #374151;
            -fx-border-radius: 8;
            -fx-padding: 10 14;
        """);

        // ── Card ──────────────────────────────────────────────────────
        VBox card = new VBox(12,
            new VBox(3, titulo, subtitulo),
            tip,
            tabla
        );
        card.setPadding(new Insets(20));
        card.setStyle("""
            -fx-background-color: #1f2937;
            -fx-background-radius: 12;
            -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.3), 10, 0, 0, 3);
        """);
        VBox.setVgrow(card, Priority.ALWAYS);

        VBox root = new VBox(16, card);
        root.setPadding(new Insets(24));
        root.setStyle("-fx-background-color: #111827;");
        VBox.setVgrow(root, Priority.ALWAYS);
        return root;
    }

    private <S,T> TableColumn<S,T> col(String title, String prop, double minWidth) {
        TableColumn<S,T> c = new TableColumn<>(title);
        c.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>(prop));
        c.setMinWidth(minWidth);
        return c;
    }
}
