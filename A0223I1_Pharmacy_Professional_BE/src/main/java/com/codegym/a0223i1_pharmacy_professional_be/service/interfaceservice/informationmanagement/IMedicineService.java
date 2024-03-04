package com.codegym.a0223i1_pharmacy_professional_be.service.interfaceservice.informationmanagement;

import com.codegym.a0223i1_pharmacy_professional_be.dto.MedicineDto;

public interface IMedicineService {
    MedicineDto findMedicineById(String id);

}
