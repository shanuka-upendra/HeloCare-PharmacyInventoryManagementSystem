package com.dev.model;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Inventory {
    private Integer batchID;
    private Integer medicineID;
    private Integer supplierID;
    private String batchNumber;
    private LocalDate manufactureDate;
    private LocalDate expiryDate;
    private Integer qtyInStock;
    private Double costPrice;
    private Double sellingPrice;
}
