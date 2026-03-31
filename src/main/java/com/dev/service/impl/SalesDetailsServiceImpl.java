package com.dev.service.impl;

import com.dev.model.SalesDetails;
import com.dev.repository.SalesDetailsRepository;
import com.dev.repository.impl.SalesDetailsRepositoryImpl;
import com.dev.service.SalesDetailsService;
import javafx.collections.ObservableList;

public class SalesDetailsServiceImpl implements SalesDetailsService {
    
    SalesDetailsRepository salesDetailsRepository = new SalesDetailsRepositoryImpl();

    @Override
    public void addSaleDetails(SalesDetails saleDetails) {
        salesDetailsRepository.addSaleDetails(saleDetails);
    }

    @Override
    public void updateSaleDetails(SalesDetails saleDetails) {
        salesDetailsRepository.updateSaleDetails(saleDetails);
    }

    @Override
    public void deleteSaleDetails(Integer saleDetailsId) {
        salesDetailsRepository.deleteSaleDetails(saleDetailsId);
    }

    @Override
    public SalesDetails searchSaleDetailsById(Integer saleDetailsId) {
        return salesDetailsRepository.searchSaleDetailsById(saleDetailsId);
    }

    @Override
    public ObservableList<SalesDetails> getAllSalesDetails() {
        return salesDetailsRepository.getAllSalesDetails();
    }
}
