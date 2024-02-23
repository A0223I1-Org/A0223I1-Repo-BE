package com.codegym.a0223i1_pharmacy_professional_be.repository.medicinemanagement;

import com.codegym.a0223i1_pharmacy_professional_be.entity.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MedicineRepository extends JpaRepository<Medicine, String> {
    @Query(value = "select id, name from medicine where id= :id", nativeQuery = true)
    Optional<Medicine> findById(@Param("id") String id);
}
