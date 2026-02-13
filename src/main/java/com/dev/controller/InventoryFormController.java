package com.dev.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class InventoryFormController {

    @FXML
    private TableColumn<?, ?> colBatchNumber;

    @FXML
    private TableColumn<?, ?> colBrandName1;

    @FXML
    private TableColumn<?, ?> colCostPrice;

    @FXML
    private TableColumn<?, ?> colExpDate;

    @FXML
    private TableColumn<?, ?> colManDate;

    @FXML
    private TableColumn<?, ?> colMedicineID;

    @FXML
    private TableColumn<?, ?> colPrice;

    @FXML
    private TableColumn<?, ?> colQtyOnHand;

    @FXML
    private TableColumn<?, ?> colSellingPrice;

    @FXML
    private TableColumn<?, ?> colStockID;

    @FXML
    private TableColumn<?, ?> colSupplierId;

    @FXML
    private TableView<?> tblInventory;

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

    }

    @FXML
    void btnDeleteInventoryOnAction(ActionEvent event) {

    }

    @FXML
    void btnSearchInventoryOnAction(ActionEvent event) {

    }

    @FXML
    void btnUpdateInventoryOnAction(ActionEvent event) {

    }

}
