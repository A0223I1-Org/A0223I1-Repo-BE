package com.codegym.a0223i1_pharmacy_professional_be.service.implement.informationmanagement;

import com.codegym.a0223i1_pharmacy_professional_be.entity.MedicineGroup;
import com.codegym.a0223i1_pharmacy_professional_be.repository.informationmanagement.MedicineGroupRepository;
import com.codegym.a0223i1_pharmacy_professional_be.service.interfaceservice.informationmanagement.MedicineGroupService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class MedicineGroupServiceImpl implements MedicineGroupService {
    @Autowired
    MedicineGroupRepository medicineGroupRepository;

    @Override
    public List<MedicineGroup> getAllMedicineGroup() {
        return medicineGroupRepository.getAllMedicineGroup();
    }

    @Override
    public void create(MedicineGroup medicineGroup) {
        medicineGroupRepository.createMedicineGroup(medicineGroup.getMedicineGroupName());
    }

    @Override
    public void update(MedicineGroup medicineGroup) {
        medicineGroupRepository.updateMedicineGroupName(medicineGroup.getMedicineGroupId(), medicineGroup.getMedicineGroupName());
    }

    @Override
    public void delete(MedicineGroup medicineGroup) {
        medicineGroupRepository.deleteByMedicineGroupId(medicineGroup.getMedicineGroupId());
    }

    @Override
    public MedicineGroup getMedicineGroupById(Integer id) {
        return medicineGroupRepository.findByMedicineGroupId(id);
    }
}
