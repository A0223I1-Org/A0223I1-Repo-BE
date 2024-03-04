package com.codegym.a0223i1_pharmacy_professional_be.repository.informationmanagement;

import com.codegym.a0223i1_pharmacy_professional_be.entity.MedicineGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MedicineGroupRepository extends JpaRepository<MedicineGroup, Integer> {
    @Query(value = "SELECT * FROM Medicine_Group", nativeQuery = true)
    List<MedicineGroup> getAllMedicineGroup();

    @Query(value = "SELECT * FROM Medicine_Group WHERE medicine_group_id = ?1", nativeQuery = true)
    MedicineGroup findByMedicineGroupId(Integer medicineGroupId);

    @Modifying
    @Query(value = "INSERT INTO Medicine_Group (medicine_group_name) VALUES (?1)", nativeQuery = true)
    void createMedicineGroup(String medicineGroupName);

    @Modifying
    @Query(value = "UPDATE Medicine_Group SET medicine_group_name = ?2 WHERE medicine_group_id = ?1", nativeQuery = true)
    void updateMedicineGroupName(Integer medicineGroupId, String medicineGroupName);

    @Modifying
    @Query(value = "DELETE FROM Medicine_Group WHERE medicine_group_id = ?1", nativeQuery = true)
    void deleteByMedicineGroupId(Integer medicineGroupId);

}
