package com.codegym.a0223i1_pharmacy_professional_be.service.implement.informationmanagement;

import com.codegym.a0223i1_pharmacy_professional_be.entity.Medicine;
import com.codegym.a0223i1_pharmacy_professional_be.repository.informationmanagement.MedicineInformationRepository;
import com.codegym.a0223i1_pharmacy_professional_be.service.interfaceservice.informationmanagement.MedicineInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MedicineInformationServiceImpl implements MedicineInformationService {
    @Autowired
    MedicineInformationRepository medicineInformationRepository;

    @Override
    public List<Medicine> getAllMedicine() {
        return medicineInformationRepository.findAll();
    }

    @Override
    public Medicine findMedicineById(String id) {
        return medicineInformationRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteMedicine(Medicine medicine) {
        medicineInformationRepository.delete(medicine);
    }
}
