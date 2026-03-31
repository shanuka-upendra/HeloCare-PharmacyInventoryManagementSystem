package com.dev.service;

import com.dev.model.SalesAndBilling;
import javafx.collections.ObservableList;

public interface SalesAndBillingService {
    void addSale(SalesAndBilling sale);
    void updateSale(SalesAndBilling sale);
    void deleteSale(Integer saleId);
    SalesAndBilling searchSaleById(Integer saleId);
    ObservableList<SalesAndBilling> getAllSales();
}

