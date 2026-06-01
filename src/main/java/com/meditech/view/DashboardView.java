package com.meditech.view;

import com.meditech.database.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class DashboardView {

    public PieChart graficaEstados = new PieChart();

    public VBox crearVista() {

        // ── Encabezado ────────────────────────────────────────────────
        Label titulo = new Label("Dashboard");
        titulo.setFont(Font.font("Segoe UI", FontWeight.BOLD, 24));
        titulo.setStyle("-fx-text-fill: white;");

        Label subtitulo = new Label("Resumen del sistema médico");
        subtitulo.setStyle("-fx-text-fill: #9ca3af; -fx-font-size: 13px;");

        VBox header = new VBox(4, titulo, subtitulo);
        header.setPadding(new Insets(0, 0, 16, 0));

        // ── Tarjetas de estadísticas ──────────────────────────────────
        HBox tarjetas = new HBox(16,
            crearTarjeta("👥", "Pacientes",   "—", "#2563eb"),
            crearTarjeta("📅", "Citas hoy",   "—", "#059669"),
            crearTarjeta("✅", "Atendidas",    "—", "#7c3aed"),
            crearTarjeta("⏳", "Pendientes",   "—", "#d97706")
        );
        tarjetas.setPadding(new Insets(0, 0, 20, 0));

        // ── Gráfica ───────────────────────────────────────────────────
        graficaEstados.setTitle("Estado de Citas");
        graficaEstados.setStyle("-fx-font-size: 13px;");
        graficaEstados.setPrefHeight(320);
        graficaEstados.setLabelLineLength(20);
        graficaEstados.setLegendVisible(true);

        VBox chartCard = new VBox(8,
            styledLabel("Distribución de Citas", true),
            graficaEstados
        );
        chartCard.setPadding(new Insets(20));
        chartCard.setStyle("""
            -fx-background-color: #1f2937;
            -fx-background-radius: 12;
            -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.3), 12, 0, 0, 4);
        """);

        // ── Root ──────────────────────────────────────────────────────
        VBox root = new VBox(0, header, tarjetas, chartCard);
        root.setPadding(new Insets(24));
        root.setStyle("-fx-background-color: #111827;");
        VBox.setVgrow(chartCard, Priority.ALWAYS);
        return root;
    }

    private VBox crearTarjeta(String icono, String titulo, String valor, String color) {
        Label ico   = new Label(icono);
        ico.setFont(Font.font(22));

        Label tit = new Label(titulo);
        tit.setStyle("-fx-text-fill: #9ca3af; -fx-font-size: 12px;");

        Label val = new Label(valor);
        val.setFont(Font.font("Segoe UI", FontWeight.BOLD, 28));
        val.setStyle("-fx-text-fill: white;");

        Region accent = new Region();
        accent.setPrefHeight(4);
        accent.setStyle("-fx-background-color: " + color + "; -fx-background-radius: 2;");

        VBox card = new VBox(6, ico, tit, val, accent);
        card.setPadding(new Insets(16, 20, 16, 20));
        card.setStyle("""
            -fx-background-color: #1f2937;
            -fx-background-radius: 12;
            -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.3), 10, 0, 0, 3);
        """);
        HBox.setHgrow(card, Priority.ALWAYS);
        return card;
    }

    private Label styledLabel(String text, boolean bold) {
        Label l = new Label(text);
        l.setStyle("-fx-text-fill: #e5e7eb; -fx-font-size: 14px;"
            + (bold ? " -fx-font-weight: bold;" : ""));
        return l;
    }
}
