package com.codegym.a0223i1_pharmacy_professional_be.service.interfaceservice.informationmanagement;


import com.codegym.a0223i1_pharmacy_professional_be.entity.MedicineGroup;

import java.util.List;

public interface MedicineGroupService {
    List<MedicineGroup> getAllMedicineGroup();
    void create(MedicineGroup medicineGroup);
    void update(MedicineGroup medicineGroup);
    void delete(MedicineGroup medicineGroup);
    MedicineGroup getMedicineGroupById(Integer id);
}
