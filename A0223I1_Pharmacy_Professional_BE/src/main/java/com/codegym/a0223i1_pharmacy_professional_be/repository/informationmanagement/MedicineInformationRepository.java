package com.codegym.a0223i1_pharmacy_professional_be.repository.informationmanagement;

import com.codegym.a0223i1_pharmacy_professional_be.entity.Medicine;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MedicineInformationRepository extends JpaRepository<Medicine, String> {

    @Query(value = "SELECT * FROM medicine", nativeQuery = true)
    List<Medicine> getAllMedicine();

    @Query(value = "SELECT * FROM medicine WHERE medicine_id = :id", nativeQuery = true)
    Medicine getMedicineById(String id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM medicine WHERE medicine_id = :medicineId", nativeQuery = true)
    void deleteMedicine(String medicineId);
}
