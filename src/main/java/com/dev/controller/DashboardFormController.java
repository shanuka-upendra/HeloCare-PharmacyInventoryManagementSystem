package com.dev.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class DashboardFormController {
    Stage stage = new Stage();

    @FXML
    void btnDashboardOnAction(ActionEvent event) {
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/dashboard_form.fxml"))));
            stage.show();
            stage.setTitle("HeloCare - Dashboard");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnInventoryOnAction(ActionEvent event) {

    }

    @FXML
    void btnMedicineOnAction(ActionEvent event) {
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/medicine_form.fxml"))));
            stage.show();
            stage.setTitle("HeloCare - Medicine");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void btnSalesBillingOnAction(ActionEvent event) {

    }

    @FXML
    void btnSupplierOnAction(ActionEvent event) {

    }

}
