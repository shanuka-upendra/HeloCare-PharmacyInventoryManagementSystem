package com.dev.service.impl;

import com.dev.model.Inventory;
import com.dev.repository.InevntoryRepository;
import com.dev.repository.impl.InventoryRepositoryImpl;
import com.dev.service.InventoryService;
import javafx.collections.ObservableList;

public class InevntoryServiceImpl implements InventoryService {

    InevntoryRepository inevntoryRepository = new InventoryRepositoryImpl();

    @Override
    public ObservableList<Inventory> getAllDetails() {
        return inevntoryRepository.getAllDetails();
    }

    @Override
    public void addStock(Inventory inventory) {
        inevntoryRepository.addStock(inventory);
    }

    @Override
    public void updateStock(Inventory inventory) {
        inevntoryRepository.updateStock(inventory);
    }

    @Override
    public void deleteStock(Integer id) {
        inevntoryRepository.deleteStock(id);
    }

    @Override
    public Inventory searchStockById(Integer id) {
        return inevntoryRepository.searchStockById(id);
    }
}
