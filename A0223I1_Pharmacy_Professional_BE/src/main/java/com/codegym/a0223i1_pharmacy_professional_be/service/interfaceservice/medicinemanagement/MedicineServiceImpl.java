package com.codegym.a0223i1_pharmacy_professional_be.service.interfaceservice.medicinemanagement;

import com.codegym.a0223i1_pharmacy_professional_be.entity.Medicine;
import com.codegym.a0223i1_pharmacy_professional_be.repository.medicinemanagement.MedicineRepository;
import com.codegym.a0223i1_pharmacy_professional_be.service.implement.medicinemanagement.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicineServiceImpl implements MedicineService {
    private MedicineRepository medicineRepository;

    @Autowired
    public MedicineServiceImpl(MedicineRepository medicineRepository) {
        this.medicineRepository = medicineRepository;
    }

    @Override
    public List<Medicine> findAll() {
        return medicineRepository.findAll();
    }
    @Override
    public Optional<Medicine> findById(String id) {
        return medicineRepository.findById(id);
    }

    public Medicine findById2(String id) {
        Optional<Medicine> optionalMedicine = medicineRepository.findById(id);
        return optionalMedicine.orElse(null);
    }

}