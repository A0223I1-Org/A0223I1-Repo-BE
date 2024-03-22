package com.codegym.a0223i1_pharmacy_professional_be.repository.informationmanagement;

import com.codegym.a0223i1_pharmacy_professional_be.entity.Symptom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SymptomRepository extends JpaRepository<Symptom, Integer> {
    @Query(value = "SELECT s.*, p.prescription_name AS pname " +
            "FROM symptom s " +
            "JOIN prescription p ON s.symptom_id = p.symptom_id " +
            "WHERE p.prescription_id = :id", nativeQuery = true)
    Symptom findSymptomByPrescriptionId(@Param("id") String id);


}
