package com.dev.controller;

import com.dev.model.Supplier;
import com.dev.service.SupplierService;
import com.dev.service.impl.SupplierServiceImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SupplierFormController implements Initializable {

    SupplierService supplierService = new SupplierServiceImpl();

    @FXML
    private TableView<Supplier> tblSupplier;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colContactPerson;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colPhoneNo;

    @FXML
    private TableColumn<?, ?> colSupName;

    @FXML
    private TableColumn<?, ?> colSupplierId;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtPhone;

    @FXML
    private TextField txtSupplierContact;

    @FXML
    private TextField txtSupplierID;

    @FXML
    private TextField txtSupplierName;

    @FXML
    void btnAddSupplierOnAction(ActionEvent event) {
        try {
            // Simple validation
            if (!validateSupplierFields()) {
                return;
            }
            
            supplierService.addSupplier(new Supplier(
                    Integer.parseInt(txtSupplierID.getText()),
                    txtSupplierName.getText(),
                    txtSupplierContact.getText(),
                    txtPhone.getText(),
                    txtEmail.getText(),
                    txtAddress.getText()
            ));

            showSuccess("Supplier added successfully!");
            loadTable();
            clearText();
        } catch (NumberFormatException e) {
            showError("Supplier ID must be a valid number!");
        } catch (Exception e) {
            showError("Failed to add supplier: " + e.getMessage());
        }
    }

    @FXML
    void btnDeleteSupplierOnAction(ActionEvent event) {
        try {
            // Check if Supplier ID is provided
            if (txtSupplierID.getText().isEmpty()) {
                showError("Please enter Supplier ID to delete!");
                return;
            }
            
            supplierService.deleteSupplier(Integer.parseInt(txtSupplierID.getText()));
            showSuccess("Supplier deleted successfully!");
            loadTable();
            clearText();
        } catch (NumberFormatException e) {
            showError("Supplier ID must be a valid number!");
        } catch (Exception e) {
            showError("Failed to delete supplier: " + e.getMessage());
        }
    }

    @FXML
    void btnSearchSupplierOnAction(ActionEvent event) {
        try {
            // Check if Supplier ID is provided
            if (txtSupplierID.getText().isEmpty()) {
                showError("Please enter Supplier ID to search!");
                return;
            }
            
            Supplier supplier = supplierService.searchSupplierById(Integer.parseInt(txtSupplierID.getText()));

            if(supplier != null) {
                for (Supplier supplierItems : tblSupplier.getItems()) {
                    if (supplierItems.getSupplierId().equals(supplier.getSupplierId())) {
                        tblSupplier.getSelectionModel().select(supplierItems);
                        tblSupplier.scrollTo(supplierItems);
                        break;
                    }
                }
            }else {
                showError("Supplier Not Found!");
            }
        } catch (NumberFormatException e) {
            showError("Supplier ID must be a valid number!");
        } catch (Exception e) {
            showError("Search failed: " + e.getMessage());
        }
    }

    @FXML
    void btnUpdateSupplierOnAction(ActionEvent event) {
        try {
            // Simple validation
            if (!validateSupplierFields()) {
                return;
            }
            
            supplierService.updateSupplier(new Supplier(
                    Integer.parseInt(txtSupplierID.getText()),
                    txtSupplierName.getText(),
                    txtSupplierContact.getText(),
                    txtPhone.getText(),
                    txtEmail.getText(),
                    txtAddress.getText()
            ));

            showSuccess("Supplier updated successfully!");
            loadTable();
            clearText();
        } catch (NumberFormatException e) {
            showError("Supplier ID must be a valid number!");
        } catch (Exception e) {
            showError("Failed to update supplier: " + e.getMessage());
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadTable();

        colSupplierId.setCellValueFactory(new PropertyValueFactory<>("supplierId"));
        colSupName.setCellValueFactory(new PropertyValueFactory<>("supplierName"));
        colContactPerson.setCellValueFactory(new PropertyValueFactory<>("contactPerson"));
        colPhoneNo.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));


    }

    void loadTable(){
        tblSupplier.setItems(supplierService.getAllSuppliers());
    }

    void clearText(){
        txtSupplierID.clear();
        txtSupplierName.clear();
        txtSupplierContact.clear();
        txtAddress.clear();
        txtEmail.clear();
        txtPhone.clear();
    }
    
    // Simple validation method for supplier fields
    private boolean validateSupplierFields() {
        // Check empty fields
        if (txtSupplierID.getText().isEmpty()) {
            showError("Please enter Supplier ID!");
            return false;
        }
        if (txtSupplierName.getText().isEmpty()) {
            showError("Please enter Supplier Name!");
            return false;
        }
        if (txtSupplierContact.getText().isEmpty()) {
            showError("Please enter Contact Person!");
            return false;
        }
        if (txtPhone.getText().isEmpty()) {
            showError("Please enter Phone Number!");
            return false;
        }
        if (txtEmail.getText().isEmpty()) {
            showError("Please enter Email!");
            return false;
        }
        if (txtAddress.getText().isEmpty()) {
            showError("Please enter Address!");
            return false;
        }
        
        // Validate Supplier ID is a number
        try {
            Integer.parseInt(txtSupplierID.getText());
        } catch (NumberFormatException e) {
            showError("Supplier ID must be a number!");
            return false;
        }
        
        // Simple email validation - just check if it contains @
        if (!txtEmail.getText().contains("@")) {
            showError("Please enter a valid email address!");
            return false;
        }
        
        // Simple phone validation - check if it contains only numbers and spaces
        String phone = txtPhone.getText().replaceAll(" ", "");
        if (!phone.matches("\\d+")) {
            showError("Phone number should contain only numbers!");
            return false;
        }
        
        return true;
    }
    
    // Show error message
    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error - HeloCare");
        alert.setHeaderText(message);
        alert.showAndWait();
    }
    
    // Show success message
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
