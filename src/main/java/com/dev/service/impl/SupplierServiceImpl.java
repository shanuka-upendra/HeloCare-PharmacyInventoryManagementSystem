package com.dev.service.impl;

import com.dev.model.Supplier;
import com.dev.repository.SupplierRepository;
import com.dev.repository.impl.SupplierRepositoryImpl;
import com.dev.service.SupplierService;
import javafx.collections.ObservableList;

public class SupplierServiceImpl implements SupplierService {

    SupplierRepository supplierRepository = new SupplierRepositoryImpl();

    @Override
    public ObservableList<Supplier> getAllSuppliers() {
        return supplierRepository.getAllSuppliers();
    }

    @Override
    public void addSupplier(Supplier supplier) {
        supplierRepository.addSupplier(supplier);
    }

    @Override
    public void updateSupplier(Supplier supplier) {
        supplierRepository.updateSupplier(supplier);
    }

    @Override
    public void deleteSupplier(Integer id) {
        supplierRepository.deleteSupplier(id);
    }

    @Override
    public Supplier searchSupplierById(Integer id) {
        return supplierRepository.searchSupplierById(id);
    }
}
