package com.example.ficheropropiedades.controller;

import com.example.ficheropropiedades.ManagementFile;
import com.example.ficheropropiedades.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Clase SignUpController
 *
 * Es la parte logica de la interfaz de registro
 */
public class SignUpController implements Initializable {
    @FXML
    private TextField nameTxt;
    @FXML
    private PasswordField passwordTxt;
    private ManagementFile managementFile;
    private User user;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        managementFile = new ManagementFile();
    }

    // Registra a un usuario
    public void signUp(ActionEvent actionEvent) {
        if (!(nameTxt.getText().isEmpty() || passwordTxt.getText().isEmpty())){
            user = new User(nameTxt.getText(),managementFile.hashPassword(passwordTxt.getText()));
            managementFile.writeProperties(user);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Se registro al usuario correctamente.");
            alert.showAndWait();
            nameTxt.setText("");
            passwordTxt.setText("");
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Para registrase debe relleanr todos los campos");
            alert.showAndWait();
        }
    }

}
