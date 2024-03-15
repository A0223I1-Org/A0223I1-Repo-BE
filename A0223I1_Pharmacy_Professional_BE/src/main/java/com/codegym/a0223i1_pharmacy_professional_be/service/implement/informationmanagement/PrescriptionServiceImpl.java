package com.codegym.a0223i1_pharmacy_professional_be.service.implement.informationmanagement;

import com.codegym.a0223i1_pharmacy_professional_be.entity.Prescription;
import com.codegym.a0223i1_pharmacy_professional_be.entity.Symptom;
import com.codegym.a0223i1_pharmacy_professional_be.repository.informationmanagement.PrescriptionRepository;
import com.codegym.a0223i1_pharmacy_professional_be.repository.informationmanagement.SymptomRepository;
import com.codegym.a0223i1_pharmacy_professional_be.service.interfaceservice.informationmanagement.PrescriptionService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrescriptionServiceImpl implements PrescriptionService {
    private PrescriptionRepository prescriptionRepository;
    private SymptomRepository symptomRepository;


    @Autowired
    public PrescriptionServiceImpl(PrescriptionRepository prescriptionRepository, SymptomRepository symptomRepository) {
        this.prescriptionRepository = prescriptionRepository;
        this.symptomRepository = symptomRepository;
    }

    @Override
    public List<Prescription> findAll() {
        return prescriptionRepository.findAllPrescription();
    }

    @Override
    @Transactional
    public Prescription addPrescription(Prescription prescription, Symptom symptom) {
        Symptom savedSymptom = symptomRepository.save(symptom);

        prescription.setSymptom(savedSymptom);

        return prescriptionRepository.save(prescription);
    }

    @Override
    public Prescription addPrescription(Prescription prescription) {
        return prescriptionRepository.save(prescription);
    }

    @Override
    @Transactional
    public Prescription updatePrescription(Prescription prescription, Symptom symptom) {
        Symptom savedSymptom = symptomRepository.saveAndFlush(symptom);
        prescription.setSymptom(savedSymptom);
        return prescriptionRepository.saveAndFlush(prescription);
    }

    @Override
    public Prescription findPrescriptionById(String id) {
        return prescriptionRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void deletePrescription(String id) {
        prescriptionRepository.deletePrescription(id);
    }

    @Override
    public Prescription findPrescriptionByDetailId(Long id) {
        return prescriptionRepository.findPrescriptionByDetailId(id);
    }

    @Override
    public Prescription findPrescriptionByName(String name) {
        return prescriptionRepository.findPrescriptionByName(name);
    }


    @Override
    public  String generateNextCode() {
        List<Prescription> prescriptions = prescriptionRepository.findAll();

        int maxCode = 0;
        for (Prescription p : prescriptions) {
            if (p.getPrescriptionId() != null) {
                String code = p.getPrescriptionId().substring(2); // Remove the "TT" prefix
                try {
                    int codeValue = Integer.parseInt(code);
                    if (codeValue > maxCode) {
                        maxCode = codeValue;
                    }
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        }

        String nextCode = String.format("TT%04d", maxCode + 1);

        return nextCode;
    }



}