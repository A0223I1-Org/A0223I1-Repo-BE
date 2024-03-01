package com.codegym.a0223i1_pharmacy_professional_be.service.implement.prescriptionmanagement;


import com.codegym.a0223i1_pharmacy_professional_be.entity.Symptom;

import java.util.List;

public interface SymptomService {
    List<Symptom> findAll();
    Symptom addSymptom(Symptom symptom);
    Symptom findSymptomByPrescriptionId(String id);
}
