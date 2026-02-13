package com.dev.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;

public class SupplierFormController {

    @FXML
    private TableColumn<?, ?> colBatchNumber;

    @FXML
    private TableColumn<?, ?> colExpDate;

    @FXML
    private TableColumn<?, ?> colManDate;

    @FXML
    private TableColumn<?, ?> colPrice;

    @FXML
    private TableColumn<?, ?> colQtyOnHand;

    @FXML
    private TableColumn<?, ?> colSupplierId;

    @FXML
    private TextField txtCostPrice;

    @FXML
    private TextField txtMedicineID;

    @FXML
    private TextField txtQtyOnStock;

    @FXML
    private TextField txtSellingPrice;

    @FXML
    private TextField txtStockID;

    @FXML
    private TextField txtSupplierID;

    @FXML
    void btnAddSupplierOnAction(ActionEvent event) {

    }

    @FXML
    void btnDeleteSupplierOnAction(ActionEvent event) {

    }

    @FXML
    void btnSearchSupplierOnAction(ActionEvent event) {

    }

    @FXML
    void btnUpdateSupplierOnAction(ActionEvent event) {

    }

}
