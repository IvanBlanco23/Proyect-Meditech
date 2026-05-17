package com.meditech.controller;

import com.meditech.MainApp;
import com.meditech.view.LoginView;

import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class LoginController {

    private final LoginView view;

    private final Stage stage;

    public LoginController(
            LoginView view,
            Stage stage
    ) {

        this.view = view;

        this.stage = stage;

        eventos();
    }

    private void eventos() {

        view.btnLogin.setOnAction(e -> {

            String usuario =
                    view.txtUsuario
                            .getText();

            String password =
                    view.txtPassword
                            .getText();

            if (

                    usuario.equals("admin")

                            &&

                            password.equals("1234")

            ) {

                MainApp.mostrarSistema(
                        stage
                );

            } else {

                Alert alert =
                        new Alert(
                                Alert.AlertType.ERROR
                        );

                alert.setTitle("Error");

                alert.setHeaderText(null);

                alert.setContentText(
                        "Credenciales incorrectas"
                );

                alert.showAndWait();
            }
        });
    }
}