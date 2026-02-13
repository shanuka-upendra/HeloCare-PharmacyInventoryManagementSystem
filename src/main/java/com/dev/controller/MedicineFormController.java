package com.dev.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;

public class MedicineFormController {

    @FXML
    private ComboBox<?> cmbBrandName;

    @FXML
    private TableColumn<?, ?> colBrandName;

    @FXML
    private TableColumn<?, ?> colExpDate;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colPrice;

    @FXML
    private TableColumn<?, ?> colQuantity;

    @FXML
    private DatePicker txtDateExp;

    @FXML
    private TextField txtID;

    @FXML
    private TextField txtMedicineName;

    @FXML
    private TextField txtPrice;

    @FXML
    private TextField txtQuantity;

    @FXML
    void btnAddMedicineOnAction(ActionEvent event) {

    }

    @FXML
    void btnDeleteMedicineOnAction(ActionEvent event) {

    }

    @FXML
    void btnSearchMedicineOnAction(ActionEvent event) {

    }

    @FXML
    void btnUpdateMedicineOnAction(ActionEvent event) {

    }

}
