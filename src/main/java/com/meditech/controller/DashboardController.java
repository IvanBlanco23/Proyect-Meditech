package com.meditech.controller;

import com.meditech.database.EstadisticaDAO;
import com.meditech.view.DashboardView;

import javafx.scene.chart.PieChart;
import java.util.Map;

public class DashboardController {

    private final DashboardView view;
    private final EstadisticaDAO dao=new EstadisticaDAO();

    public DashboardController(DashboardView view) {
        this.view = view;
        cargarGrafica();
    }

    private void cargarGrafica() {

        Map<String, Integer> datos=dao.obtenerCitasPorEstado();

        for(Map.Entry<String, Integer> entry:datos.entrySet()){

            view.graficaEstados.getData().add(
                    new PieChart.Data(
                            entry.getKey(),
                            entry.getValue()
                    )
            );
        }
    }
}
