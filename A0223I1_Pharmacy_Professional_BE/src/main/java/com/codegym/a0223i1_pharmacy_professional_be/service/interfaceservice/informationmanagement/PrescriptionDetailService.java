package com.codegym.a0223i1_pharmacy_professional_be.service.interfaceservice.informationmanagement;

import com.codegym.a0223i1_pharmacy_professional_be.entity.PrescriptionDetail;

import java.util.List;

public interface PrescriptionDetailService {

    List<PrescriptionDetail> findAll();
    PrescriptionDetail save (PrescriptionDetail detailPrescription);
    PrescriptionDetail findById(Long id);
    PrescriptionDetail updateDetailPrescription(PrescriptionDetail detailPrescription);
}
