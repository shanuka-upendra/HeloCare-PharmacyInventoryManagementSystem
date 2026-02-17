package com.dev.controller;

import com.dev.model.Inventory;
import com.dev.service.InventoryService;
import com.dev.service.impl.InevntoryServiceImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class InventoryFormController implements Initializable {

    InventoryService inventoryService = new InevntoryServiceImpl();

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

        loadTable();
    }

    @FXML
    void btnDeleteInventoryOnAction(ActionEvent event) {
        inventoryService.deleteStock(Integer.parseInt(txtStockID.getText()));
        loadTable();

    }

    @FXML
    void btnSearchInventoryOnAction(ActionEvent event) {
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
            new Alert(Alert.AlertType.WARNING,"Inventory or Stock Record Not Found!").show();

        }
    }

    @FXML
    void btnUpdateInventoryOnAction(ActionEvent event) {
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

        loadTable();

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
}
