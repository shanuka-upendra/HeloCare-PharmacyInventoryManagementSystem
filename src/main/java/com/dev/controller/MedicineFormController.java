package com.dev.controller;

import com.dev.model.Medicine;
import com.dev.service.MedicineService;
import com.dev.service.impl.MedicineServiceImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

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
        medicineService.addMedicine(new Medicine(
                Integer.parseInt(txtID.getText()),
                txtMedicineName.getText(),
                txtCategory.getText(),
                txtBrandName.getText(),
                txtDescription.getText()
        ));

        loadTable();
    }

    @FXML
    void btnDeleteMedicineOnAction(ActionEvent event) {
        medicineService.deleteMedicine(Integer.parseInt(txtID.getText()));
        loadTable();
    }

    @FXML
    void btnSearchMedicineOnAction(ActionEvent event) {
        Medicine medicine = medicineService.searchMedicineById(Integer.parseInt(txtID.getText()));

        if(medicine != null) {
            //setDataToFields(medicine);

            for (Medicine medItems : tblMedicine.getItems()){
                if(medItems.getId().equals(medicine.getId())){
                    tblMedicine.getSelectionModel().select(medItems);
                    tblMedicine.scrollTo(medItems);
                    break;
                }
            }
        }else {
            new Alert(Alert.AlertType.WARNING,"Medicine Not Found!").show();
        }
    }

    @FXML
    void btnUpdateMedicineOnAction(ActionEvent event) {
        medicineService.updateMedicine(new Medicine(
                Integer.parseInt(txtID.getText()),
                txtMedicineName.getText(),
                txtCategory.getText(),
                txtBrandName.getText(),
                txtDescription.getText()
        ));

        loadTable();
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
}
