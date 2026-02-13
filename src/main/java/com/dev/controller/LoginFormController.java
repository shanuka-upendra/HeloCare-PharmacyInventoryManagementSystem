package com.dev.controller;

import com.mysql.cj.xdevapi.Warning;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginFormController {

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtUsername;

    @FXML
    public void btnLoginOnAction(ActionEvent event) {
        if(txtUsername.getText().equals("Shanu") || txtPassword.getText().equals("2525")){
            Stage stage = new Stage();
            try {
                stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/dashboard_form.fxml"))));
                stage.show();
                stage.setTitle("Dashboard - HeloCare");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error - HeloCare");
            alert.setHeaderText("Incorrect Username or Password");
            alert.showAndWait();

            txtUsername.clear();
            txtPassword.clear();
        }
    }

}
