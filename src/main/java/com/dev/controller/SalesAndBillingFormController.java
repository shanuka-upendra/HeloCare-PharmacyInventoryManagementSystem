package com.dev.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;

public class SalesAndBillingFormController {

    @FXML
    private ComboBox<?> cmbPaymentMethod;

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
    private DatePicker txtDate;


    @FXML
    private TextField txtSaleID;

    @FXML
    private TextField txtTaxAmount;

    @FXML
    private TextField txtTotalAmount;

    @FXML
    void btnAddSaleOnAction(ActionEvent event) {

    }

    @FXML
    void btnDeleteSaleOnAction(ActionEvent event) {

    }

    @FXML
    void btnSearchSaleOnAction(ActionEvent event) {

    }

    @FXML
    void btnUpdateSaleOnAction(ActionEvent event) {

    }

}
