package com.dev.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SalesDetails {
    private Integer saleDetailsId;
    private Integer saleId;
    private Integer batchId;
    private Integer quantitySold;
    private Double unitPrice;
    private Double subTotal;
}
