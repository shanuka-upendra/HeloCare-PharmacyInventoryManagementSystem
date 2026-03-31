package com.dev.repository;

import com.dev.model.SalesDetails;
import javafx.collections.ObservableList;

public interface SalesDetailsRepository {
    void addSaleDetails(SalesDetails saleDetails);
    void updateSaleDetails(SalesDetails saleDetails);
    void deleteSaleDetails(Integer saleDetailsId);
    SalesDetails searchSaleDetailsById(Integer saleDetailsId);
    ObservableList<SalesDetails> getAllSalesDetails();
}
