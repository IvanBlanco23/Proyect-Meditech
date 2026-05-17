package com.meditech.view;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class LoginView {

    public TextField txtUsuario= new TextField();
    public PasswordField txtPassword=new PasswordField();
    public Button btnLogin= new Button("Iniciar Sesion");

    public VBox crearVista(){

        Label titulo=new  Label("Meditech");

        titulo.setStyle("""
                        -fx-font-size: 30px;
                        -fx-font-weight: bold;
                        -fx-font-fill: #2563eb;
                        """
        );

        txtUsuario.setPromptText("Usuario");

        txtPassword.setPromptText("COntraseña");

        VBox root = new VBox(
                15,
                titulo,
                txtUsuario,
                txtPassword,
                btnLogin
        );

        root.setAlignment(Pos.CENTER);

        root.setPrefWidth(400);
        root.setMaxWidth(200);
        return root;
    }
}
