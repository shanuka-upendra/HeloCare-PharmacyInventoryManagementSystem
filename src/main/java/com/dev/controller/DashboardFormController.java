package com.dev.controller;

import com.dev.service.*;
import com.dev.service.impl.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DashboardFormController implements Initializable {

    @FXML
    private Button btnDashboard;

    @FXML
    private Label txtTotalCustomers;    // For supplier count

    @FXML
    private Label txtAllCustomers;      // For sales count

    @FXML
    private Label txtAllMedicines;      // For medicine count

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadDashboardData();
    }

    /**
     * Load real-time statistics for dashboard
     */
    private void loadDashboardData() {
        try {
            // Load medicine count
            MedicineService medicineService = new MedicineServiceImpl();
            int medicineCount = medicineService.getAllMedicines().size();
            if (txtAllMedicines != null) {
                txtAllMedicines.setText(String.valueOf(medicineCount));
            }

            // Load supplier count
            SupplierService supplierService = new SupplierServiceImpl();
            int supplierCount = supplierService.getAllSuppliers().size();
            if (txtTotalCustomers != null) {
                txtTotalCustomers.setText(String.valueOf(supplierCount));
            }

            // Load sales count
            SalesAndBillingService salesService = new SalesAndBillingImpl();
            int salesCount = salesService.getAllSales().size();
            if (txtAllCustomers != null) {
                txtAllCustomers.setText(String.valueOf(salesCount));
            }

        } catch (Exception e) {
            System.out.println("Dashboard data loading error: " + e.getMessage());
            // Set default values if loading fails
            if (txtAllMedicines != null) txtAllMedicines.setText("0");
            if (txtTotalCustomers != null) txtTotalCustomers.setText("0");
            if (txtAllCustomers != null) txtAllCustomers.setText("0");
        }
    }

    // Helper method to get the current stage
    private Stage getCurrentStage(ActionEvent event) {
        return (Stage) ((Button) event.getSource()).getScene().getWindow();
    }

    // Helper method to navigate to a new scene in the same window
    private void navigateTo(String fxmlPath, String title, ActionEvent event) {
        try {
            Stage stage = getCurrentStage(event);
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource(fxmlPath)));
            stage.setScene(scene);
            stage.setTitle(title);
        } catch (IOException e) {
            System.err.println("Failed to load: " + fxmlPath);
            e.printStackTrace();
        }
    }

    @FXML
    void btnDashboardOnAction(ActionEvent event) {
        navigateTo("/view/dashboard_form.fxml", "HeloCare - Dashboard", event);
    }

    @FXML
    void btnInventoryOnAction(ActionEvent event) {
        navigateTo("/view/inventory_form.fxml", "HeloCare - Inventory", event);
    }

    @FXML
    void btnMedicineOnAction(ActionEvent event) {
        navigateTo("/view/medicine_form.fxml", "HeloCare - Medicine", event);
    }

    @FXML
    void btnSupplierOnAction(ActionEvent event) {
        navigateTo("/view/supplier_form.fxml", "HeloCare - Supplier", event);
    }

    @FXML
    void btnSalesBillingOnAction(ActionEvent event) {
        navigateTo("/view/sales_and_billing_form.fxml", "HeloCare - Sales & Billing", event);
    }

    @FXML
    public void btnSaleDetailsOnAction(ActionEvent event) {
        navigateTo("/view/sales_details_form.fxml", "HeloCare - Sale Details", event);
    }
}


