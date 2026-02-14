package com.dev.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;

public class SalesDetailsFormController {

    @FXML
    private ComboBox<?> cmbPaymentMethod;

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
    private TextField txtDiscountAmount;

    @FXML
    private TextField txtSaleID;

    @FXML
    private TextField txtTaxAmount;

    @FXML
    private TextField txtTotalAmount;

    @FXML
    void btnAddSaleDetailsOnAction(ActionEvent event) {

    }

    @FXML
    void btnDeleteSaleDetailsOnAction(ActionEvent event) {

    }

    @FXML
    void btnSearchSaleDetailsOnAction(ActionEvent event) {

    }

    @FXML
    void btnUpdateSaleDetailsOnAction(ActionEvent event) {

    }

}
