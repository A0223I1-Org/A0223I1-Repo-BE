package com.codegym.a0223i1_pharmacy_professional_be.dto;

import com.codegym.a0223i1_pharmacy_professional_be.entity.Prescription;
import com.codegym.a0223i1_pharmacy_professional_be.entity.Symptom;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PrescriptionDetailDTO {
    private String name;
    private String target;
    private String treatmentPeriod;
    private Boolean deleteFlag;
    private String note;
    private Symptom symptom;
    private String times;
    private String quantity;
    private String quantityPerTimes;
    private String medicineId;
    private Prescription prescription;
    private List<PrescriptionDetailDTO> detailPrescription ;

    private String times2;
    private String quantity2;
    private String quantityPerTimes2;
    private String medicineId2;

    private String times3;
    private String quantity3;
    private String quantityPerTimes3;
    private String medicineId3;

    private String times4;
    private String quantity4;
    private String quantityPerTimes4;
    private String medicineId4;


    private String times5;
    private String quantity5;
    private String quantityPerTimes5;
    private String medicineId5;


    private String times6;
    private String quantity6;
    private String quantityPerTimes6;
    private String medicineId6;


    private String times7;
    private String quantity7;
    private String quantityPerTimes7;
    private String medicineId7;
}

