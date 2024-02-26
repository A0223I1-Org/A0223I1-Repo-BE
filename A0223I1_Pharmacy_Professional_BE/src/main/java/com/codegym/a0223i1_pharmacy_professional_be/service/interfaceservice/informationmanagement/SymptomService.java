package com.codegym.a0223i1_pharmacy_professional_be.service.interfaceservice.informationmanagement;

import com.codegym.a0223i1_pharmacy_professional_be.entity.Symptom;

import java.util.List;

public interface SymptomService {
    List<Symptom> findAll();
    Symptom findSymptomByPrescriptionId(String id);
    Symptom findById (Integer id);
}
