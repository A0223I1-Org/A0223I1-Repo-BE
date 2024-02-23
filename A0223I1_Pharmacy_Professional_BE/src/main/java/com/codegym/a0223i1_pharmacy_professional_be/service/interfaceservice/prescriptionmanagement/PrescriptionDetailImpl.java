package com.codegym.a0223i1_pharmacy_professional_be.service.interfaceservice.prescriptionmanagement;

import com.codegym.a0223i1_pharmacy_professional_be.entity.PrescriptionDetail;
import com.codegym.a0223i1_pharmacy_professional_be.entity.Symptom;
import com.codegym.a0223i1_pharmacy_professional_be.repository.prescriptionmanagement.PrescriptionDetailRepository;
import com.codegym.a0223i1_pharmacy_professional_be.service.implement.prescriptionmanagement.PrescriptionDetailService;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrescriptionDetailImpl implements PrescriptionDetailService {
    private PrescriptionDetailRepository detailPrescriptionRepository;

    @Autowired
    public PrescriptionDetailImpl(PrescriptionDetailRepository detailPrescriptionRepository) {
        this.detailPrescriptionRepository = detailPrescriptionRepository;
    }

    @Override
    public List<PrescriptionDetail> findAll() {
        return detailPrescriptionRepository.findAll();
    }

    @Override
    @Transactional
    public PrescriptionDetail save(PrescriptionDetail detailPrescription) {
            return detailPrescriptionRepository.save(detailPrescription);
    }

    @Override
    public PrescriptionDetail findById(Long id) {
        return detailPrescriptionRepository.findById(id).orElse(null);
    }



    @Override
    @Transactional
    public PrescriptionDetail updateDetailPrescription(PrescriptionDetail detailPrescription) {
        return detailPrescriptionRepository.saveAndFlush(detailPrescription);
    }
}
