package com.codegym.a0223i1_pharmacy_professional_be.service.interfaceservice.informationmanagement;

import com.codegym.a0223i1_pharmacy_professional_be.entity.Prescription;
import com.codegym.a0223i1_pharmacy_professional_be.entity.Symptom;

import java.util.List;

public interface PrescriptionService {
    List<Prescription> findAll();

    Prescription addPrescription(Prescription prescription, Symptom symptom);

    Prescription addPrescription(Prescription prescription);

    String generateNextCode();

    Prescription updatePrescription(Prescription prescription, Symptom symptom);

    Prescription findPrescriptionById(String id);
    void deletePrescription(String id);
    Prescription findPrescriptionByDetailId(Long id);
}
