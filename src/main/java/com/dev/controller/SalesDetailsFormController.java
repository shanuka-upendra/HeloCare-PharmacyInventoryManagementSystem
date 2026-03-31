package com.dev.controller;

import com.dev.model.SalesDetails;
import com.dev.service.SalesDetailsService;
import com.dev.service.impl.SalesDetailsServiceImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SalesDetailsFormController implements Initializable {

    SalesDetailsService salesDetailsService = new SalesDetailsServiceImpl();

    @FXML
    private TableColumn<?, ?> colBatchId;

    @FXML
    private TableColumn<?, ?> colQuantitySold;

    @FXML
    private TableColumn<?, ?> colSaleDetailsID;

    @FXML
    private TableColumn<?, ?> colSaleId;

    @FXML
    private TableColumn<?, ?> colSubTotal;

    @FXML
    private TableColumn<?, ?> colUnitPrice;

    @FXML
    private TableView<SalesDetails> tblSalesDetails;

    @FXML
    private TextField txtBatchID;

    @FXML
    private TextField txtQtySold;

    @FXML
    private TextField txtSaleDetailsID;

    @FXML
    private TextField txtSaleID;

    @FXML
    private TextField txtSubTotal;

    @FXML
    private TextField txtUnitAmount;

    @FXML
    void btnAddSaleDetailsOnAction(ActionEvent event) {
        try {
            // Validate fields
            if (!validateSaleDetailsFields()) {
                return;
            }

            // Calculate subtotal automatically
            double unitPrice = Double.parseDouble(txtUnitAmount.getText());
            int quantity = Integer.parseInt(txtQtySold.getText());
            double subTotal = unitPrice * quantity;
            txtSubTotal.setText(String.valueOf(subTotal));

            salesDetailsService.addSaleDetails(new SalesDetails(
                    Integer.parseInt(txtSaleDetailsID.getText()),
                    Integer.parseInt(txtSaleID.getText()),
                    Integer.parseInt(txtBatchID.getText()),
                    quantity,
                    unitPrice,
                    subTotal
            ));

            showSuccess("Sale Details added successfully!");
            loadTable();
            clearFields();
        } catch (NumberFormatException e) {
            showError("Please enter valid numbers for all fields!");
        } catch (Exception e) {
            showError("Failed to add sale details: " + e.getMessage());
        }
    }

    @FXML
    void btnDeleteSaleDetailsOnAction(ActionEvent event) {
        try {
            if (txtSaleDetailsID.getText().isEmpty()) {
                showError("Please enter Sale Details ID to delete!");
                return;
            }

            salesDetailsService.deleteSaleDetails(Integer.parseInt(txtSaleDetailsID.getText()));
            showSuccess("Sale Details deleted successfully!");
            loadTable();
            clearFields();
        } catch (NumberFormatException e) {
            showError("Sale Details ID must be a valid number!");
        } catch (Exception e) {
            showError("Failed to delete sale details: " + e.getMessage());
        }
    }

    @FXML
    void btnSearchSaleDetailsOnAction(ActionEvent event) {
        try {
            if (txtSaleDetailsID.getText().isEmpty()) {
                showError("Please enter Sale Details ID to search!");
                return;
            }

            SalesDetails saleDetails = salesDetailsService.searchSaleDetailsById(Integer.parseInt(txtSaleDetailsID.getText()));

            if (saleDetails != null) {
                for (SalesDetails item : tblSalesDetails.getItems()) {
                    if (item.getSaleDetailsId().equals(saleDetails.getSaleDetailsId())) {
                        tblSalesDetails.getSelectionModel().select(item);
                        tblSalesDetails.scrollTo(item);
                        
                        // Fill fields with found data
                        txtSaleID.setText(String.valueOf(saleDetails.getSaleId()));
                        txtBatchID.setText(String.valueOf(saleDetails.getBatchId()));
                        txtQtySold.setText(String.valueOf(saleDetails.getQuantitySold()));
                        txtUnitAmount.setText(String.valueOf(saleDetails.getUnitPrice()));
                        txtSubTotal.setText(String.valueOf(saleDetails.getSubTotal()));
                        break;
                    }
                }
            } else {
                showError("Sale Details Not Found!");
            }
        } catch (NumberFormatException e) {
            showError("Sale Details ID must be a valid number!");
        } catch (Exception e) {
            showError("Search failed: " + e.getMessage());
        }
    }

    @FXML
    void btnUpdateSaleDetailsOnAction(ActionEvent event) {
        try {
            // Validate fields
            if (!validateSaleDetailsFields()) {
                return;
            }

            // Calculate subtotal automatically
            double unitPrice = Double.parseDouble(txtUnitAmount.getText());
            int quantity = Integer.parseInt(txtQtySold.getText());
            double subTotal = unitPrice * quantity;
            txtSubTotal.setText(String.valueOf(subTotal));

            salesDetailsService.updateSaleDetails(new SalesDetails(
                    Integer.parseInt(txtSaleDetailsID.getText()),
                    Integer.parseInt(txtSaleID.getText()),
                    Integer.parseInt(txtBatchID.getText()),
                    quantity,
                    unitPrice,
                    subTotal
            ));

            showSuccess("Sale Details updated successfully!");
            loadTable();
            clearFields();
        } catch (NumberFormatException e) {
            showError("Please enter valid numbers for all fields!");
        } catch (Exception e) {
            showError("Failed to update sale details: " + e.getMessage());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            // Set up table columns
            colSaleDetailsID.setCellValueFactory(new PropertyValueFactory<>("saleDetailsId"));
            colSaleId.setCellValueFactory(new PropertyValueFactory<>("saleId"));
            colBatchId.setCellValueFactory(new PropertyValueFactory<>("batchId"));
            colQuantitySold.setCellValueFactory(new PropertyValueFactory<>("quantitySold"));
            colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
            colSubTotal.setCellValueFactory(new PropertyValueFactory<>("subTotal"));
            
            loadTable();
        } catch (Exception e) {
            showError("Failed to initialize Sales Details form. Please ensure the 'sales_details' table exists in your database. Run setup_database.sql script.");
            System.err.println("Initialization error: " + e.getMessage());
        }
    }

    void loadTable() {
        tblSalesDetails.setItems(salesDetailsService.getAllSalesDetails());
    }

    void clearFields() {
        txtSaleDetailsID.clear();
        txtSaleID.clear();
        txtBatchID.clear();
        txtQtySold.clear();
        txtUnitAmount.clear();
        txtSubTotal.clear();
    }

    // Simple validation method
    private boolean validateSaleDetailsFields() {
        if (txtSaleDetailsID.getText().isEmpty()) {
            showError("Please enter Sale Details ID!");
            return false;
        }
        if (txtSaleID.getText().isEmpty()) {
            showError("Please enter Sale ID!");
            return false;
        }
        if (txtBatchID.getText().isEmpty()) {
            showError("Please enter Batch ID!");
            return false;
        }
        if (txtQtySold.getText().isEmpty()) {
            showError("Please enter Quantity Sold!");
            return false;
        }
        if (txtUnitAmount.getText().isEmpty()) {
            showError("Please enter Unit Price!");
            return false;
        }

        // Validate numbers
        try {
            Integer.parseInt(txtSaleDetailsID.getText());
            Integer.parseInt(txtSaleID.getText());
            Integer.parseInt(txtBatchID.getText());
            int quantity = Integer.parseInt(txtQtySold.getText());
            double unitPrice = Double.parseDouble(txtUnitAmount.getText());

            if (quantity <= 0) {
                showError("Quantity Sold must be greater than zero!");
                return false;
            }
            if (unitPrice < 0) {
                showError("Unit Price cannot be negative!");
                return false;
            }
        } catch (NumberFormatException e) {
            showError("All IDs, Quantity, and Price must be valid numbers!");
            return false;
        }

        return true;
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error - HeloCare");
        alert.setHeaderText(message);
        alert.showAndWait();
    }

    private void showSuccess(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success - HeloCare");
        alert.setHeaderText(message);
        alert.show();
    }

    // ============ NAVIGATION METHODS ============
    
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
