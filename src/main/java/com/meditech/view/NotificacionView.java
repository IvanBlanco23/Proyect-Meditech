package com.meditech.view;

import com.meditech.model.Notificacion;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class NotificacionView {

    // ── Campo público requerido por NotificacionController ────────────
    public ListView<Notificacion> lista = new ListView<>();

    public VBox crearVista() {

        // ── Encabezado ────────────────────────────────────────────────
        Label titulo = new Label("🔔 Notificaciones");
        titulo.setFont(Font.font("Segoe UI", FontWeight.BOLD, 22));
        titulo.setStyle("-fx-text-fill: white;");
        Label subtitulo = new Label("Alertas y avisos del sistema");
        subtitulo.setStyle("-fx-text-fill: #9ca3af; -fx-font-size: 12px;");

        // ── Badge contador ────────────────────────────────────────────
        Label badge = new Label("0 nuevas");
        badge.setStyle("""
            -fx-background-color: #dc2626;
            -fx-text-fill: white;
            -fx-background-radius: 12;
            -fx-padding: 2 10;
            -fx-font-size: 11px;
            -fx-font-weight: bold;
        """);

        HBox headerRow = new HBox();
        headerRow.setAlignment(Pos.CENTER_LEFT);
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        headerRow.getChildren().addAll(new VBox(3, titulo, subtitulo), spacer, badge);

        // ── Lista ─────────────────────────────────────────────────────
        lista.setStyle("""
            -fx-background-color: #1f2937;
            -fx-border-color: #374151;
            -fx-border-radius: 8;
            -fx-background-radius: 8;
        """);
        lista.setPlaceholder(crearPlaceholder());
        VBox.setVgrow(lista, Priority.ALWAYS);

        // ── Actualizar badge al cambiar la lista ──────────────────────
        lista.getItems().addListener((javafx.collections.ListChangeListener<Notificacion>) change -> {
            int n = lista.getItems().size();
            badge.setText(n + (n == 1 ? " nueva" : " nuevas"));
        });

        // ── Card ──────────────────────────────────────────────────────
        VBox card = new VBox(12, headerRow, lista);
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

    private VBox crearPlaceholder() {
        Label icon = new Label("🔕");
        icon.setFont(Font.font(32));
        Label msg = new Label("Sin notificaciones");
        msg.setStyle("-fx-text-fill: #6b7280; -fx-font-size: 14px;");
        Label sub = new Label("Los avisos del sistema aparecerán aquí");
        sub.setStyle("-fx-text-fill: #4b5563; -fx-font-size: 12px;");
        VBox box = new VBox(6, icon, msg, sub);
        box.setAlignment(Pos.CENTER);
        return box;
    }
}
