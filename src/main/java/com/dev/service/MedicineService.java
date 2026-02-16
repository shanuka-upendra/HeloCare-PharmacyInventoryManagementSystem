package com.dev.service;

import com.dev.model.Medicine;
import javafx.collections.ObservableList;

public interface MedicineService {
    ObservableList<Medicine> getAllMedicines();
    void addMedicine(Medicine medicine);
    void updateMedicine(Medicine medicine);
    void deleteMedicine(Integer id);
    Medicine searchMedicineById(Integer id);
}
