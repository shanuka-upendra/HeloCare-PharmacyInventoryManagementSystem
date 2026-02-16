package com.dev.repository.impl;

import com.dev.db.DBConnection;
import com.dev.model.Supplier;
import com.dev.repository.SupplierRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SupplierRepositoryImpl implements SupplierRepository {
    @Override
    public ObservableList<Supplier> getAllSuppliers() {
        ObservableList<Supplier> supplierObservableList = FXCollections.observableArrayList();
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Suppliers");

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                supplierObservableList.add(new Supplier(
                        resultSet.getInt("SupplierID"),
                        resultSet.getString("SupplierName"),
                        resultSet.getString("ContactPerson"),
                        resultSet.getString("PhoneNumber"),
                        resultSet.getString("Email"),
                        resultSet.getString("Address")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return supplierObservableList;
    }

    @Override
    public void addSupplier(Supplier supplier) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Suppliers VALUES (?,?,?,?,?,?)");

            preparedStatement.setObject(1,supplier.getSupplierId());
            preparedStatement.setObject(2,supplier.getSupplierName());
            preparedStatement.setObject(3,supplier.getContactPerson());
            preparedStatement.setObject(4,supplier.getPhoneNumber());
            preparedStatement.setObject(5,supplier.getEmail());
            preparedStatement.setObject(6,supplier.getAddress());

            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateSupplier(Supplier supplier) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Suppliers SET SupplierName = ? , ContactPerson = ? , PhoneNumber = ? , Email = ? , Address = ? WHERE SupplierID = ? ");

            preparedStatement.setObject(1,supplier.getSupplierName());
            preparedStatement.setObject(2,supplier.getContactPerson());
            preparedStatement.setObject(3,supplier.getPhoneNumber());
            preparedStatement.setObject(4,supplier.getEmail());
            preparedStatement.setObject(5,supplier.getAddress());
            preparedStatement.setObject(6,supplier.getSupplierId());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteSupplier(Integer id) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM suppliers WHERE supplierID = ? ");
            preparedStatement.setObject(1,id);

            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Supplier searchSupplierById(Integer id) {
        Supplier supplier = null;
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM suppliers WHERE supplierID = ?");

            preparedStatement.setObject(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                 supplier = new Supplier(
                        resultSet.getInt("SupplierID"),
                        resultSet.getString("SupplierName"),
                        resultSet.getString("ContactPerson"),
                        resultSet.getString("PhoneNumber"),
                        resultSet.getString("Email"),
                        resultSet.getString("Address")
                );
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return supplier;
    }
}
