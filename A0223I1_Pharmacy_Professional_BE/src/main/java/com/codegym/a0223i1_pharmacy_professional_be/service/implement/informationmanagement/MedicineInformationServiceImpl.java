package com.codegym.a0223i1_pharmacy_professional_be.service.implement.informationmanagement;

import com.codegym.a0223i1_pharmacy_professional_be.entity.Medicine;
import com.codegym.a0223i1_pharmacy_professional_be.repository.informationmanagement.MedicineInformationRepository;
import com.codegym.a0223i1_pharmacy_professional_be.service.interfaceservice.informationmanagement.MedicineInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicineInformationServiceImpl implements MedicineInformationService {
    @Autowired
    MedicineInformationRepository medicineInformationRepository;

    @Override
    public List<Medicine> getAllMedicine() {
        return medicineInformationRepository.getAllMedicine();
    }

    @Override
    public Medicine findMedicineById(String id) {
        return medicineInformationRepository.getMedicineById(id);
    }

    @Override
    public void deleteMedicine(Medicine medicine) {
        medicineInformationRepository.deleteMedicine(medicine.getMedicineId());
    }

    @Override
    public Optional<Medicine> findById(String id) {
        return medicineInformationRepository.findById(id);
    }

    public Medicine findById2(String id) {
        Optional<Medicine> optionalMedicine = medicineInformationRepository.findById(id);
        return optionalMedicine.orElse(null);
    }

}
