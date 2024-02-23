package com.codegym.a0223i1_pharmacy_professional_be.service.implement.medicinemanagement;

import com.codegym.a0223i1_pharmacy_professional_be.entity.Medicine;

import java.util.List;
import java.util.Optional;

public interface MedicineService  {
    List<Medicine> findAll();

    Optional<Medicine> findById(String id);
    Medicine findById2(String id);
}

