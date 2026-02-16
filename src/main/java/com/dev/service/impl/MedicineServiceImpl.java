package com.dev.service.impl;

import com.dev.model.Medicine;
import com.dev.repository.MedicineRepository;
import com.dev.repository.impl.MedicineRepositoryImpl;
import com.dev.service.MedicineService;
import javafx.collections.ObservableList;

public class MedicineServiceImpl implements MedicineService {

    MedicineRepository medicineRepository = new MedicineRepositoryImpl();

    @Override
    public ObservableList<Medicine> getAllMedicines() {
        return medicineRepository.getAllMedicines();
    }

    @Override
    public void addMedicine(Medicine medicine) {
        medicineRepository.addMedicine(medicine);
    }

    @Override
    public void updateMedicine(Medicine medicine) {
        medicineRepository.updateMedicine(medicine);
    }

    @Override
    public void deleteMedicine(Integer id) {
        medicineRepository.deleteMedicine(id);
    }

    @Override
    public Medicine searchMedicineById(Integer id) {
        return medicineRepository.searchMedicineById(id);
    }
}
