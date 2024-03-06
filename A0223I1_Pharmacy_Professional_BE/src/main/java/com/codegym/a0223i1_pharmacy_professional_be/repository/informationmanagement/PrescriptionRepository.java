package com.codegym.a0223i1_pharmacy_professional_be.repository.informationmanagement;


import com.codegym.a0223i1_pharmacy_professional_be.entity.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PrescriptionRepository extends JpaRepository<Prescription, String> {
    @Query(value = "SELECT p.id, p.name, p.target, p.note, p.treatment_period, p.delete_flag, symptom_id, s.name AS symptomName " +
            "FROM prescription p " +
            "JOIN symptom s ON p.symptom_id = s.id " +
            "WHERE p.delete_flag = 1", nativeQuery = true)
    List<Prescription> findAllPrescription();

    @Modifying
    @Query(value = "UPDATE prescription SET delete_flag = 0 WHERE id = :id", nativeQuery = true)
    void deletePrescription(@Param("id") String id);

    @Query(value = "SELECT p.id, p.name, p.target, p.note, p.treatment_period, p.delete_flag, p.symptom_id " +
            "FROM prescription p " +
            "JOIN detail_prescription dp ON dp.pres_id = p.id " +
            "WHERE dp.id = :id", nativeQuery = true)
    Prescription findPrescriptionByDetailId(@Param("id") Long id);



}
