package com.dev.repository;

import com.dev.model.Inventory;
import javafx.collections.ObservableList;

public interface InevntoryRepository {
    ObservableList<Inventory> getAllDetails();
    void addStock(Inventory inventory);
    void updateStock(Inventory inventory);
    void deleteStock(Integer id);
    Inventory searchStockById(Integer id);
}
