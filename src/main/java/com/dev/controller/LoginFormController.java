package com.dev.controller;

import com.dev.db.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginFormController {

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtUsername;

    @FXML
    public void btnLoginOnAction(ActionEvent event) {
        // Simple validation - check if fields are empty
        if (txtUsername.getText().isEmpty() || txtPassword.getText().isEmpty()) {
            showError("Please enter both username and password");
            return;
        }
        
        try {
            // Check username and password against database
            if (authenticateUser(txtUsername.getText(), txtPassword.getText())) {
                // Login successful - open dashboard
                Stage stage = new Stage();
                stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/dashboard_form.fxml"))));
                stage.show();
                stage.setTitle("Dashboard - HeloCare");
                
                // Close login window
                ((Stage) txtUsername.getScene().getWindow()).close();
            } else {
                // Login failed
                showError("Incorrect Username or Password");
                txtUsername.clear();
                txtPassword.clear();
            }
        } catch (Exception e) {
            showError("Login error: " + e.getMessage());
        }
    }
    
    // Simple method to check username and password from database
    private boolean authenticateUser(String username, String password) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            String query = "SELECT * FROM users WHERE username = ? AND password = ?";
            PreparedStatement pstm = connection.prepareStatement(query);
            pstm.setString(1, username);
            pstm.setString(2, password);
            
            ResultSet resultSet = pstm.executeQuery();
            return resultSet.next(); // Returns true if user found
            
        } catch (Exception e) {
            System.out.println("Authentication error: " + e.getMessage());
            return false;
        }
    }
    
    // Simple method to show error messages
    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error - HeloCare");
        alert.setHeaderText(message);
        alert.showAndWait();
    }

}
