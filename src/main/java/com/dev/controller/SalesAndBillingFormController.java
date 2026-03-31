package com.dev.controller;

import com.dev.model.SalesAndBilling;
import com.dev.service.SalesAndBillingService;
import com.dev.service.impl.SalesAndBillingImpl;
import javafx.collections.FXCollections;
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

public class SalesAndBillingFormController implements Initializable {

    SalesAndBillingService salesService = new SalesAndBillingImpl();

    @FXML
    private ComboBox<String> cmbPaymentMethod;

    @FXML
    private TableColumn<?, ?> colPaymentMethod;

    @FXML
    private TableColumn<?, ?> colSaleId;

    @FXML
    private TableColumn<?, ?> colTaxAmount;

    @FXML
    private TableColumn<?, ?> colTotalAmount;

    @FXML
    private TableColumn<?, ?> colTransactionTotal;

    @FXML
    private TableView<SalesAndBilling> tblSales;

    @FXML
    private DatePicker txtDate;

    @FXML
    private TextField txtSaleID;

    @FXML
    private TextField txtTaxAmount;

    @FXML
    private TextField txtTotalAmount;

    @FXML
    void btnAddSaleOnAction(ActionEvent event) {
        try {
            // Validate fields
            if (!validateSaleFields()) {
                return;
            }

            salesService.addSale(new SalesAndBilling(
                    Integer.parseInt(txtSaleID.getText()),
                    txtDate.getValue(),
                    Double.parseDouble(txtTotalAmount.getText()),
                    cmbPaymentMethod.getValue(),
                    Double.parseDouble(txtTaxAmount.getText())
            ));

            showSuccess("Sale added successfully!");
            loadTable();
            clearFields();
        } catch (NumberFormatException e) {
            showError("Please enter valid numbers for Sale ID, Total Amount, and Tax Amount!");
        } catch (Exception e) {
            showError("Failed to add sale: " + e.getMessage());
        }
    }

    @FXML
    void btnDeleteSaleOnAction(ActionEvent event) {
        try {
            if (txtSaleID.getText().isEmpty()) {
                showError("Please enter Sale ID to delete!");
                return;
            }

            salesService.deleteSale(Integer.parseInt(txtSaleID.getText()));
            showSuccess("Sale deleted successfully!");
            loadTable();
            clearFields();
        } catch (NumberFormatException e) {
            showError("Sale ID must be a valid number!");
        } catch (Exception e) {
            showError("Failed to delete sale: " + e.getMessage());
        }
    }

    @FXML
    void btnSearchSaleOnAction(ActionEvent event) {
        try {
            if (txtSaleID.getText().isEmpty()) {
                showError("Please enter Sale ID to search!");
                return;
            }

            SalesAndBilling sale = salesService.searchSaleById(Integer.parseInt(txtSaleID.getText()));

            if (sale != null) {
                for (SalesAndBilling saleItem : tblSales.getItems()) {
                    if (saleItem.getSaleId().equals(sale.getSaleId())) {
                        tblSales.getSelectionModel().select(saleItem);
                        tblSales.scrollTo(saleItem);
                        
                        // Fill fields with found data
                        txtDate.setValue(sale.getTransactionDate());
                        txtTotalAmount.setText(String.valueOf(sale.getTotalAmount()));
                        cmbPaymentMethod.setValue(sale.getPaymentMethod());
                        txtTaxAmount.setText(String.valueOf(sale.getTaxAmount()));
                        break;
                    }
                }
            } else {
                showError("Sale Not Found!");
            }
        } catch (NumberFormatException e) {
            showError("Sale ID must be a valid number!");
        } catch (Exception e) {
            showError("Search failed: " + e.getMessage());
        }
    }

    @FXML
    void btnUpdateSaleOnAction(ActionEvent event) {
        try {
            // Validate fields
            if (!validateSaleFields()) {
                return;
            }

            salesService.updateSale(new SalesAndBilling(
                    Integer.parseInt(txtSaleID.getText()),
                    txtDate.getValue(),
                    Double.parseDouble(txtTotalAmount.getText()),
                    cmbPaymentMethod.getValue(),
                    Double.parseDouble(txtTaxAmount.getText())
            ));

            showSuccess("Sale updated successfully!");
            loadTable();
            clearFields();
        } catch (NumberFormatException e) {
            showError("Please enter valid numbers for Sale ID, Total Amount, and Tax Amount!");
        } catch (Exception e) {
            showError("Failed to update sale: " + e.getMessage());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            // Set up payment method combo box
            cmbPaymentMethod.setItems(FXCollections.observableArrayList("Cash", "Card", "Mobile Payment", "Bank Transfer"));
            
            // Set up table columns
            colSaleId.setCellValueFactory(new PropertyValueFactory<>("saleId"));
            colTransactionTotal.setCellValueFactory(new PropertyValueFactory<>("transactionDate"));
            colTotalAmount.setCellValueFactory(new PropertyValueFactory<>("totalAmount"));
            colPaymentMethod.setCellValueFactory(new PropertyValueFactory<>("paymentMethod"));
            colTaxAmount.setCellValueFactory(new PropertyValueFactory<>("taxAmount"));
            
            loadTable();
        } catch (Exception e) {
            showError("Failed to initialize Sales form. Please ensure the 'sales' table exists in your database. Run setup_database.sql script.");
            System.err.println("Initialization error: " + e.getMessage());
        }
    }

    void loadTable() {
        tblSales.setItems(salesService.getAllSales());
    }

    void clearFields() {
        txtSaleID.clear();
        txtDate.setValue(null);
        txtTotalAmount.clear();
        cmbPaymentMethod.setValue(null);
        txtTaxAmount.clear();
    }

    // Simple validation method
    private boolean validateSaleFields() {
        if (txtSaleID.getText().isEmpty()) {
            showError("Please enter Sale ID!");
            return false;
        }
        if (txtDate.getValue() == null) {
            showError("Please select Transaction Date!");
            return false;
        }
        if (txtTotalAmount.getText().isEmpty()) {
            showError("Please enter Total Amount!");
            return false;
        }
        if (cmbPaymentMethod.getValue() == null || cmbPaymentMethod.getValue().isEmpty()) {
            showError("Please select Payment Method!");
            return false;
        }
        if (txtTaxAmount.getText().isEmpty()) {
            showError("Please enter Tax Amount!");
            return false;
        }

        // Validate numbers
        try {
            Integer.parseInt(txtSaleID.getText());
            double totalAmount = Double.parseDouble(txtTotalAmount.getText());
            double taxAmount = Double.parseDouble(txtTaxAmount.getText());

            if (totalAmount < 0) {
                showError("Total Amount cannot be negative!");
                return false;
            }
            if (taxAmount < 0) {
                showError("Tax Amount cannot be negative!");
                return false;
            }
        } catch (NumberFormatException e) {
            showError("Sale ID, Total Amount, and Tax Amount must be valid numbers!");
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
