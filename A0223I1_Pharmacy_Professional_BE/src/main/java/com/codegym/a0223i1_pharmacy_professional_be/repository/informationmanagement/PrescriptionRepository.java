package com.codegym.a0223i1_pharmacy_professional_be.repository.informationmanagement;


import com.codegym.a0223i1_pharmacy_professional_be.entity.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PrescriptionRepository extends JpaRepository<Prescription, String> {
    @Query(value = "SELECT p.prescription_id, p.prescription_name, p.target, p.create_date, p.note, p.treatment_period, p.delete_flag, p.symptom_id, s.symptom_name AS symptomName " +
            "FROM prescription p " +
            "JOIN symptom s ON p.symptom_id = s.symptom_id " +
            "WHERE p.delete_flag = 1", nativeQuery = true)
    List<Prescription> findAllPrescription();

    @Modifying
    @Query(value = "UPDATE prescription SET delete_flag = 0 WHERE id = :id", nativeQuery = true)
    void deletePrescription(@Param("id") String id);

    @Query(value = "SELECT p.prescription_id, p.prescription_name, p.target, p.create_date, p.note, p.treatment_period, p.delete_flag, p.symptom_id " +
            "FROM prescription p " +
            "JOIN prescription_detail dp ON dp.prescription_id = p.prescription_id " +
            "WHERE dp.prescription_detail_id = :id", nativeQuery = true)
    Prescription findPrescriptionByDetailId(@Param("id") Long id);

    @Query(value = "SELECT p.prescription_id, p.prescription_name, p.target, p.note, p.create_date, p.treatment_period, p.delete_flag, p.symptom_id " +
            "FROM prescription p " +
            "WHERE p.prescription_name = :name AND p.delete_flag = 1 ", nativeQuery = true)
    Prescription findPrescriptionByName(@Param("name") String name);

}
