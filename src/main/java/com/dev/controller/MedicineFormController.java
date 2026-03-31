package com.dev.controller;

import com.dev.model.Medicine;
import com.dev.service.MedicineService;
import com.dev.service.impl.MedicineServiceImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

public class MedicineFormController implements Initializable {

    MedicineService medicineService = new MedicineServiceImpl();

    @FXML
    private TableView<Medicine> tblMedicine;

    @FXML
    private ComboBox<?> cmbBrandName;

    @FXML
    private TableColumn<?, ?> colBrandName;

    @FXML
    private TableColumn<?, ?> colCategory;

    @FXML
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TextField txtBrandName;

    @FXML
    private TextField txtCategory;

    @FXML
    private TextField txtDescription;

    @FXML
    private TextField txtID;

    @FXML
    private TextField txtMedicineName;



    ObservableList<Medicine> medicinesList = FXCollections.observableArrayList();

    @FXML
    void btnAddMedicineOnAction(ActionEvent event) {
        try {
            // Simple validation - check empty fields
            if (!validateMedicineFields()) {
                return;
            }
            
            medicineService.addMedicine(new Medicine(
                    Integer.parseInt(txtID.getText()),
                    txtMedicineName.getText(),
                    txtCategory.getText(),
                    txtBrandName.getText(),
                    txtDescription.getText()
            ));

            showSuccess("Medicine added successfully!");
            loadTable();
            clearText();
        } catch (NumberFormatException e) {
            showError("Medicine ID must be a valid number!");
        } catch (Exception e) {
            showError("Failed to add medicine: " + e.getMessage());
        }
    }

    @FXML
    void btnDeleteMedicineOnAction(ActionEvent event) {
        try {
            // Check if ID field is empty
            if (txtID.getText().isEmpty()) {
                showError("Please enter Medicine ID to delete!");
                return;
            }
            
            medicineService.deleteMedicine(Integer.parseInt(txtID.getText()));
            showSuccess("Medicine deleted successfully!");
            loadTable();
            clearText();
        } catch (NumberFormatException e) {
            showError("Medicine ID must be a valid number!");
        } catch (Exception e) {
            showError("Failed to delete medicine: " + e.getMessage());
        }
    }

    @FXML
    void btnSearchMedicineOnAction(ActionEvent event) {
        try {
            // Check if ID field is empty
            if (txtID.getText().isEmpty()) {
                showError("Please enter Medicine ID to search!");
                return;
            }
            
            Medicine medicine = medicineService.searchMedicineById(Integer.parseInt(txtID.getText()));

            if(medicine != null) {
                for (Medicine medItems : tblMedicine.getItems()){
                    if(medItems.getId().equals(medicine.getId())){
                        tblMedicine.getSelectionModel().select(medItems);
                        tblMedicine.scrollTo(medItems);
                        break;
                    }
                }
            }else {
                showError("Medicine Not Found!");
            }
        } catch (NumberFormatException e) {
            showError("Medicine ID must be a valid number!");
        } catch (Exception e) {
            showError("Search failed: " + e.getMessage());
        }
    }

    @FXML
    void btnUpdateMedicineOnAction(ActionEvent event) {
        try {
            // Simple validation - check empty fields
            if (!validateMedicineFields()) {
                return;
            }
            
            medicineService.updateMedicine(new Medicine(
                    Integer.parseInt(txtID.getText()),
                    txtMedicineName.getText(),
                    txtCategory.getText(),
                    txtBrandName.getText(),
                    txtDescription.getText()
            ));

            showSuccess("Medicine updated successfully!");
            loadTable();
            clearText();
        } catch (NumberFormatException e) {
            showError("Medicine ID must be a valid number!");
        } catch (Exception e) {
            showError("Failed to update medicine: " + e.getMessage());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadTable();

        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colBrandName.setCellValueFactory(new PropertyValueFactory<>("brandName"));
        colCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));

    }

    void loadTable(){
        tblMedicine.setItems(medicineService.getAllMedicines());
    }

    void clearText(){
        txtID.clear();
        txtMedicineName.clear();
        txtBrandName.clear();
        txtCategory.clear();
        txtDescription.clear();
    }
    
    // Simple validation method
    private boolean validateMedicineFields() {
        // Check if any field is empty
        if (txtID.getText().isEmpty()) {
            showError("Please enter Medicine ID!");
            return false;
        }
        if (txtMedicineName.getText().isEmpty()) {
            showError("Please enter Medicine Name!");
            return false;
        }
        if (txtCategory.getText().isEmpty()) {
            showError("Please enter Category!");
            return false;
        }
        if (txtBrandName.getText().isEmpty()) {
            showError("Please enter Brand Name!");
            return false;
        }
        if (txtDescription.getText().isEmpty()) {
            showError("Please enter Description!");
            return false;
        }
        
        // Check if ID is a number
        try {
            Integer.parseInt(txtID.getText());
        } catch (NumberFormatException e) {
            showError("Medicine ID must be a number!");
            return false;
        }
        
        return true;
    }
    
    // Simple method to show error messages
    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error - HeloCare");
        alert.setHeaderText(message);
        alert.showAndWait();
    }
    
    // Simple method to show success messages
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
