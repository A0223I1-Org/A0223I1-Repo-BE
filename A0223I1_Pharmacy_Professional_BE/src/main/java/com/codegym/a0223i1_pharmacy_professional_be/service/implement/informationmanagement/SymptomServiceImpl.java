package com.codegym.a0223i1_pharmacy_professional_be.service.implement.informationmanagement;

import com.codegym.a0223i1_pharmacy_professional_be.entity.Symptom;
import com.codegym.a0223i1_pharmacy_professional_be.repository.informationmanagement.SymptomRepository;
import com.codegym.a0223i1_pharmacy_professional_be.service.interfaceservice.informationmanagement.SymptomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SymptomServiceImpl implements SymptomService {
    private SymptomRepository symptomRepository;

    @Autowired
    public SymptomServiceImpl(SymptomRepository symptomRepository) {
        this.symptomRepository = symptomRepository;
    }

    @Override
    public List<Symptom> findAll() {
        return symptomRepository.findAll();
    }

    @Override
    public Symptom findSymptomByPrescriptionId(String id) {
        return symptomRepository.findSymptomByPrescriptionId(id);
    }


    @Override
    public Symptom findById(Integer id) {
        return symptomRepository.findById(id).orElse(null);
    }
}
