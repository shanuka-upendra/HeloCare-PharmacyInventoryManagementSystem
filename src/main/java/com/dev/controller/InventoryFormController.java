package com.dev.controller;

import com.dev.model.Inventory;
import com.dev.service.InventoryService;
import com.dev.service.impl.InventoryServiceImpl;
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

public class InventoryFormController implements Initializable {

    InventoryService inventoryService = new InventoryServiceImpl();

    @FXML
    private TableColumn<?, ?> colBatchNumber;

    @FXML
    private TableColumn<?, ?> colCostPrice;

    @FXML
    private TableColumn<?, ?> colExpDate;

    @FXML
    private TableColumn<?, ?> colManDate;

    @FXML
    private TableColumn<?, ?> colMedicineID;

    @FXML
    private TableColumn<?, ?> colQtyOnHand;

    @FXML
    private TableColumn<?, ?> colSellingPrice;

    @FXML
    private TableColumn<?, ?> colStockID;

    @FXML
    private TableColumn<?, ?> colSupplierId;

    @FXML
    private TableView<Inventory> tblInventory;

    @FXML
    private TextField txtCostPrice;

    @FXML
    private TextField txtIBatchNumber;

    @FXML
    private DatePicker txtManufactureDateExp;

    @FXML
    private TextField txtMedicineID;

    @FXML
    private TextField txtQtyOnStock;

    @FXML
    private TextField txtSellingPrice;

    @FXML
    private DatePicker txtStockDateExp;

    @FXML
    private TextField txtStockID;

    @FXML
    private TextField txtSupplierID;

    @FXML
    void btnAddInventoryOnAction(ActionEvent event) {
        try {
            // Simple validation - check all fields
            if (!validateInventoryFields()) {
                return;
            }
            
            inventoryService.addStock(new Inventory(
                    Integer.parseInt(txtStockID.getText()),
                    Integer.parseInt(txtMedicineID.getText()),
                    Integer.parseInt(txtSupplierID.getText()),
                    txtIBatchNumber.getText(),
                    txtManufactureDateExp.getValue(),
                    txtStockDateExp.getValue(),
                    Integer.parseInt(txtQtyOnStock.getText()),
                    Double.parseDouble(txtCostPrice.getText()),
                    Double.parseDouble(txtSellingPrice.getText())
                    ));

            showSuccess("Inventory added successfully!");
            loadTable();
            clearFields();
        } catch (NumberFormatException e) {
            showError("Please enter valid numbers for ID, Quantity, and Prices!");
        } catch (Exception e) {
            showError("Failed to add inventory: " + e.getMessage());
        }
    }

    @FXML
    void btnDeleteInventoryOnAction(ActionEvent event) {
        try {
            // Check if Stock ID is provided
            if (txtStockID.getText().isEmpty()) {
                showError("Please enter Stock ID to delete!");
                return;
            }
            
            inventoryService.deleteStock(Integer.parseInt(txtStockID.getText()));
            showSuccess("Inventory deleted successfully!");
            loadTable();
            clearFields();
        } catch (NumberFormatException e) {
            showError("Stock ID must be a valid number!");
        } catch (Exception e) {
            showError("Failed to delete inventory: " + e.getMessage());
        }
    }

    @FXML
    void btnSearchInventoryOnAction(ActionEvent event) {
        try {
            // Check if Stock ID is provided
            if (txtStockID.getText().isEmpty()) {
                showError("Please enter Stock ID to search!");
                return;
            }
            
            Inventory inventory = inventoryService.searchStockById(Integer.parseInt(txtStockID.getText()));

            if (inventory != null) {
                for (Inventory inventoryItem : tblInventory.getItems()) {
                    if (inventoryItem.getBatchID().equals(inventory.getBatchID())) {
                        tblInventory.getSelectionModel().select(inventoryItem);
                        tblInventory.scrollTo(inventoryItem);
                        break;
                    }
                }
            }else{
                showError("Inventory or Stock Record Not Found!");
            }
        } catch (NumberFormatException e) {
            showError("Stock ID must be a valid number!");
        } catch (Exception e) {
            showError("Search failed: " + e.getMessage());
        }
    }

    @FXML
    void btnUpdateInventoryOnAction(ActionEvent event) {
        try {
            // Simple validation - check all fields
            if (!validateInventoryFields()) {
                return;
            }
            
            inventoryService.updateStock(new Inventory(
                    Integer.parseInt(txtStockID.getText()),
                    Integer.parseInt(txtMedicineID.getText()),
                    Integer.parseInt(txtSupplierID.getText()),
                    txtIBatchNumber.getText(),
                    txtManufactureDateExp.getValue(),
                    txtStockDateExp.getValue(),
                    Integer.parseInt(txtQtyOnStock.getText()),
                    Double.parseDouble(txtCostPrice.getText()),
                    Double.parseDouble(txtSellingPrice.getText())
            ));

            showSuccess("Inventory updated successfully!");
            loadTable();
            clearFields();
        } catch (NumberFormatException e) {
            showError("Please enter valid numbers for ID, Quantity, and Prices!");
        } catch (Exception e) {
            showError("Failed to update inventory: " + e.getMessage());
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadTable();

        colStockID.setCellValueFactory(new PropertyValueFactory<>("batchID"));
        colMedicineID.setCellValueFactory(new PropertyValueFactory<>("medicineID"));
        colSupplierId.setCellValueFactory(new PropertyValueFactory<>("supplierID"));
        colBatchNumber.setCellValueFactory(new PropertyValueFactory<>("batchNumber"));
        colManDate.setCellValueFactory(new PropertyValueFactory<>("manufactureDate"));
        colExpDate.setCellValueFactory(new PropertyValueFactory<>("expiryDate"));
        colQtyOnHand.setCellValueFactory(new PropertyValueFactory<>("qtyInStock"));
        colCostPrice.setCellValueFactory(new PropertyValueFactory<>("costPrice"));
        colSellingPrice.setCellValueFactory(new PropertyValueFactory<>("sellingPrice"));

    }

    void loadTable(){
        tblInventory.setItems(inventoryService.getAllDetails());
    }
    
    // Simple validation method for inventory fields
    private boolean validateInventoryFields() {
        // Check empty fields
        if (txtStockID.getText().isEmpty()) {
            showError("Please enter Stock ID!");
            return false;
        }
        if (txtMedicineID.getText().isEmpty()) {
            showError("Please enter Medicine ID!");
            return false;
        }
        if (txtSupplierID.getText().isEmpty()) {
            showError("Please enter Supplier ID!");
            return false;
        }
        if (txtIBatchNumber.getText().isEmpty()) {
            showError("Please enter Batch Number!");
            return false;
        }
        if (txtManufactureDateExp.getValue() == null) {
            showError("Please select Manufacture Date!");
            return false;
        }
        if (txtStockDateExp.getValue() == null) {
            showError("Please select Expiry Date!");
            return false;
        }
        if (txtQtyOnStock.getText().isEmpty()) {
            showError("Please enter Quantity!");
            return false;
        }
        if (txtCostPrice.getText().isEmpty()) {
            showError("Please enter Cost Price!");
            return false;
        }
        if (txtSellingPrice.getText().isEmpty()) {
            showError("Please enter Selling Price!");
            return false;
        }
        
        // Validate numbers
        try {
            int stockID = Integer.parseInt(txtStockID.getText());
            int medicineID = Integer.parseInt(txtMedicineID.getText());
            int supplierID = Integer.parseInt(txtSupplierID.getText());
            int quantity = Integer.parseInt(txtQtyOnStock.getText());
            double costPrice = Double.parseDouble(txtCostPrice.getText());
            double sellingPrice = Double.parseDouble(txtSellingPrice.getText());
            
            // Check positive values
            if (quantity <= 0) {
                showError("Quantity must be greater than zero!");
                return false;
            }
            if (costPrice < 0) {
                showError("Cost Price cannot be negative!");
                return false;
            }
            if (sellingPrice < 0) {
                showError("Selling Price cannot be negative!");
                return false;
            }
            
        } catch (NumberFormatException e) {
            showError("IDs, Quantity, and Prices must be valid numbers!");
            return false;
        }
        
        // Check if expiry date is after manufacture date
        if (txtStockDateExp.getValue().isBefore(txtManufactureDateExp.getValue())) {
            showError("Expiry Date must be after Manufacture Date!");
            return false;
        }
        
        return true;
    }
    
    // Clear all fields
    private void clearFields() {
        txtStockID.clear();
        txtMedicineID.clear();
        txtSupplierID.clear();
        txtIBatchNumber.clear();
        txtManufactureDateExp.setValue(null);
        txtStockDateExp.setValue(null);
        txtQtyOnStock.clear();
        txtCostPrice.clear();
        txtSellingPrice.clear();
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
