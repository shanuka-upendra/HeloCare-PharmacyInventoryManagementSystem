package com.dev.repository.impl;

import com.dev.db.DBConnection;
import com.dev.model.Inventory;
import com.dev.repository.InevntoryRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InventoryRepositoryImpl implements InevntoryRepository {


    @Override
    public ObservableList<Inventory> getAllDetails() {
        ObservableList<Inventory> inventoryObservableList = FXCollections.observableArrayList();

        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM batches");

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                inventoryObservableList.add(new Inventory(
                        resultSet.getInt(1),
                        resultSet.getInt(2),
                        resultSet.getInt(3),
                        resultSet.getString(4),
                        resultSet.getDate(5).toLocalDate(),
                        resultSet.getDate(6).toLocalDate(),
                        resultSet.getInt(7),
                        resultSet.getDouble(8),
                        resultSet.getDouble(9)
                ));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return inventoryObservableList;
    }

    @Override
    public void addStock(Inventory inventory) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO batches VALUES (?,?,?,?,?,?,?,?,?)");

            preparedStatement.setObject(1,inventory.getBatchID());
            preparedStatement.setObject(2,inventory.getMedicineID());
            preparedStatement.setObject(3,inventory.getSupplierID());
            preparedStatement.setObject(4,inventory.getBatchNumber());
            preparedStatement.setObject(5,inventory.getManufactureDate());
            preparedStatement.setObject(6,inventory.getExpiryDate());
            preparedStatement.setObject(7,inventory.getQtyInStock());
            preparedStatement.setObject(8,inventory.getCostPrice());
            preparedStatement.setObject(9,inventory.getSellingPrice());

            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void updateStock(Inventory inventory) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE batches SET MedicineID = ? , SupplierID = ? , BatchNumber = ? , ManufactureDate = ? , ExpiryDate = ? , QuantityInStock = ? , CostPrice = ? , SellingPrice = ?  WHERE BatchID = ?");

            preparedStatement.setObject(1,inventory.getMedicineID());
            preparedStatement.setObject(2,inventory.getSupplierID());
            preparedStatement.setObject(3,inventory.getBatchNumber());
            preparedStatement.setObject(4,inventory.getManufactureDate());
            preparedStatement.setObject(5,inventory.getExpiryDate());
            preparedStatement.setObject(6,inventory.getQtyInStock());
            preparedStatement.setObject(7,inventory.getCostPrice());
            preparedStatement.setObject(8,inventory.getSellingPrice());
            preparedStatement.setObject(9,inventory.getBatchID());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void deleteStock(Integer id) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM batches WHERE BatchID = ?");

            preparedStatement.setObject(1,id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Inventory searchStockById(Integer id) {
        Inventory inventory = null;
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM batches WHERE BatchID = ?");

            preparedStatement.setObject(1,id);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                inventory = new Inventory(
                        resultSet.getInt(1),
                        resultSet.getInt(2),
                        resultSet.getInt(3),
                        resultSet.getString(4),
                        resultSet.getDate(5).toLocalDate(),
                        resultSet.getDate(6).toLocalDate(),
                        resultSet.getInt(7),
                        resultSet.getDouble(8),
                        resultSet.getDouble(9)
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return inventory;
    }
}
