package com.dev.service;

import com.dev.model.Supplier;
import javafx.collections.ObservableList;

public interface SupplierService {
    ObservableList<Supplier> getAllSuppliers();
    void addSupplier(Supplier supplier);
    void updateSupplier(Supplier supplier);
    void deleteSupplier(Integer id);
    Supplier searchSupplierById(Integer id);
}
