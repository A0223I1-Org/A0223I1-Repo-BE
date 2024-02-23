package com.codegym.a0223i1_pharmacy_professional_be.service.implement.informationmanagement;

import com.codegym.a0223i1_pharmacy_professional_be.entity.MedicineGroup;
import com.codegym.a0223i1_pharmacy_professional_be.repository.informationmanagement.MedicineGroupRepository;
import com.codegym.a0223i1_pharmacy_professional_be.service.interfaceservice.informationmanagement.MedicineGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicineGroupServiceImpl implements MedicineGroupService {
    @Autowired
    MedicineGroupRepository medicineGroupRepository;
    @Override
    public List<MedicineGroup> getAllMedicineGroup() {
        return medicineGroupRepository.findAll();
    }

    @Override
    public void create(MedicineGroup medicineGroup) {
        medicineGroupRepository.save(medicineGroup);
    }

    @Override
    public void update(MedicineGroup medicineGroup) {
        medicineGroupRepository.save(medicineGroup);
    }

    @Override
    public void delete(MedicineGroup medicineGroup) {
        medicineGroupRepository.delete(medicineGroup);
    }

    @Override
    public MedicineGroup getMedicineGroupById(Long id) {
        return medicineGroupRepository.findById(id).orElse(null);
    }
}
