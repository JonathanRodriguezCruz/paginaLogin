package com.example.ficheropropiedades.controller;

import com.example.ficheropropiedades.HelloApplication;
import com.example.ficheropropiedades.ManagementFile;
import com.example.ficheropropiedades.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Clase LoginController
 *
 * Es la parte logica de la interfaz de inicio de sesion
 */
public class LoginController implements Initializable {
    @FXML
    public TextField userNameTxt;
    @FXML
    public PasswordField passwTxt;
    public Button loginBtn;
    private ManagementFile managementFile;
    private ArrayList<User> users;
    private int cont;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        managementFile  = new ManagementFile();
        users  = new ArrayList<>();
        cont = -1;
    }

    // Inicia la sesion del usuario si se encuentra en el fichero usuario.properties
    public void loginUser(ActionEvent actionEvent) {
        userLog();
    }

    // Abre la pestaña de registro
    public void singUpUser(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("signUp-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Registrarse");
        stage.setScene(scene);
        stage.show();
    }

    // Comprueba que el nombre de usuario y la contraseña coincidan
    private void userLog() {
        users = managementFile.readProperties();
        if (!(userNameTxt.getText().isEmpty() && passwTxt.getText().isEmpty())) {
            for (int i = 0; i < users.size(); i++) {
                if (userNameTxt.getText().equals(users.get(i).getName()) && managementFile.verifyPassword(i,passwTxt.getText(),users)) {
                    cont = 1;
                    break;
                }
            }
            alerts(cont);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Para iniciar sesion debe rellenar todos los campos");
            alert.showAndWait();
        }
    }

    // Dependiendo del resultado del contador mostrara una alerta u otra
    private void alerts(int counter){
        if (counter == 1) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Se inicio sesion correctamente.");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Los datos introducidos no coinciden, comprueba que esten bien o que el usuario este registrado");
            alert.showAndWait();
        }
    }
}
