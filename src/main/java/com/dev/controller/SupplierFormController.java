package com.dev.controller;

import com.dev.model.Supplier;
import com.dev.service.SupplierService;
import com.dev.service.impl.SupplierServiceImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

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
        supplierService.addSupplier(new Supplier(
                Integer.parseInt(txtSupplierID.getText()),
                txtSupplierName.getText(),
                txtSupplierContact.getText(),
                txtPhone.getText(),
                txtEmail.getText(),
                txtAddress.getText()
        ));

        loadTable();
        clearText();

    }

    @FXML
    void btnDeleteSupplierOnAction(ActionEvent event) {
        supplierService.deleteSupplier(Integer.parseInt(txtSupplierID.getText()));
    }

    @FXML
    void btnSearchSupplierOnAction(ActionEvent event) {
       Supplier supplier =  supplierService.searchSupplierById(Integer.parseInt(txtSupplierID.getText()));

       if(supplier != null) {
           for (Supplier supplierItems : tblSupplier.getItems()) {
               if (supplierItems.getSupplierId().equals(supplier.getSupplierId())) {
                   tblSupplier.getSelectionModel().select(supplierItems);
                   tblSupplier.scrollTo(supplierItems);
                   break;
               }
           }
       }else {
           new Alert(Alert.AlertType.WARNING,"Supplier Not Found!").show();
       }
    }

    @FXML
    void btnUpdateSupplierOnAction(ActionEvent event) {
        supplierService.updateSupplier(new Supplier(
                Integer.parseInt(txtSupplierID.getText()),
                txtSupplierName.getText(),
                txtSupplierContact.getText(),
                txtPhone.getText(),
                txtEmail.getText(),
                txtAddress.getText()
        ));

        loadTable();
        clearText();

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
}
