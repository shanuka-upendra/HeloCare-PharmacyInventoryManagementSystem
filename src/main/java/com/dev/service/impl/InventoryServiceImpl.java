package com.dev.service.impl;

import com.dev.model.Inventory;
import com.dev.repository.InventoryRepository;
import com.dev.repository.impl.InventoryRepositoryImpl;
import com.dev.service.InventoryService;
import javafx.collections.ObservableList;

public class InventoryServiceImpl implements InventoryService {

    InventoryRepository inventoryRepository = new InventoryRepositoryImpl();

    @Override
    public ObservableList<Inventory> getAllDetails() {
        return inventoryRepository.getAllDetails();
    }

    @Override
    public void addStock(Inventory inventory) {
        inventoryRepository.addStock(inventory);
    }

    @Override
    public void updateStock(Inventory inventory) {
        inventoryRepository.updateStock(inventory);
    }

    @Override
    public void deleteStock(Integer id) {
        inventoryRepository.deleteStock(id);
    }

    @Override
    public Inventory searchStockById(Integer id) {
        return inventoryRepository.searchStockById(id);
    }
}
