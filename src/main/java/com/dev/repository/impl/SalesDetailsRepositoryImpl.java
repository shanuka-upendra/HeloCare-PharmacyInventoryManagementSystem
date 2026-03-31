package com.dev.repository.impl;

import com.dev.db.DBConnection;
import com.dev.model.SalesDetails;
import com.dev.repository.SalesDetailsRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SalesDetailsRepositoryImpl implements SalesDetailsRepository {

    @Override
    public void addSaleDetails(SalesDetails saleDetails) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            String query = "INSERT INTO sales_details (sale_details_id, sale_id, batch_id, quantity_sold, unit_price, sub_total) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement pstm = connection.prepareStatement(query);
            pstm.setInt(1, saleDetails.getSaleDetailsId());
            pstm.setInt(2, saleDetails.getSaleId());
            pstm.setInt(3, saleDetails.getBatchId());
            pstm.setInt(4, saleDetails.getQuantitySold());
            pstm.setDouble(5, saleDetails.getUnitPrice());
            pstm.setDouble(6, saleDetails.getSubTotal());
            pstm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateSaleDetails(SalesDetails saleDetails) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            String query = "UPDATE sales_details SET sale_id = ?, batch_id = ?, quantity_sold = ?, unit_price = ?, sub_total = ? WHERE sale_details_id = ?";
            PreparedStatement pstm = connection.prepareStatement(query);
            pstm.setInt(1, saleDetails.getSaleId());
            pstm.setInt(2, saleDetails.getBatchId());
            pstm.setInt(3, saleDetails.getQuantitySold());
            pstm.setDouble(4, saleDetails.getUnitPrice());
            pstm.setDouble(5, saleDetails.getSubTotal());
            pstm.setInt(6, saleDetails.getSaleDetailsId());
            pstm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteSaleDetails(Integer saleDetailsId) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            String query = "DELETE FROM sales_details WHERE sale_details_id = ?";
            PreparedStatement pstm = connection.prepareStatement(query);
            pstm.setInt(1, saleDetailsId);
            pstm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public SalesDetails searchSaleDetailsById(Integer saleDetailsId) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            String query = "SELECT * FROM sales_details WHERE sale_details_id = ?";
            PreparedStatement pstm = connection.prepareStatement(query);
            pstm.setInt(1, saleDetailsId);
            ResultSet resultSet = pstm.executeQuery();

            if (resultSet.next()) {
                return new SalesDetails(
                        resultSet.getInt("sale_details_id"),
                        resultSet.getInt("sale_id"),
                        resultSet.getInt("batch_id"),
                        resultSet.getInt("quantity_sold"),
                        resultSet.getDouble("unit_price"),
                        resultSet.getDouble("sub_total")
                );
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ObservableList<SalesDetails> getAllSalesDetails() {
        ObservableList<SalesDetails> salesDetailsList = FXCollections.observableArrayList();
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            String query = "SELECT * FROM sales_details";
            PreparedStatement pstm = connection.prepareStatement(query);
            ResultSet resultSet = pstm.executeQuery();

            while (resultSet.next()) {
                salesDetailsList.add(new SalesDetails(
                        resultSet.getInt("sale_details_id"),
                        resultSet.getInt("sale_id"),
                        resultSet.getInt("batch_id"),
                        resultSet.getInt("quantity_sold"),
                        resultSet.getDouble("unit_price"),
                        resultSet.getDouble("sub_total")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error loading sales details data: " + e.getMessage());
            System.err.println("Please make sure the 'sales_details' table exists in your database.");
            System.err.println("Run the SQL script: src/main/resources/setup_database.sql");
        }
        return salesDetailsList;
    }
}
