package com.meditech.view;

import com.meditech.database.*;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.chart.PieChart;

public class DashboardView {

    public PieChart graficaEstados=new PieChart();

    public VBox crearVista(){

        VBox root=new VBox(
                20,
                graficaEstados
        );
        return root;
    }

}
