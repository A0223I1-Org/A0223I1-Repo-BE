package com.codegym.a0223i1_pharmacy_professional_be.controller.prescriptiondetailmanagement;


import com.codegym.a0223i1_pharmacy_professional_be.dto.PrescriptionDetailDTO;
import com.codegym.a0223i1_pharmacy_professional_be.entity.Medicine;
import com.codegym.a0223i1_pharmacy_professional_be.entity.Prescription;
import com.codegym.a0223i1_pharmacy_professional_be.entity.PrescriptionDetail;
import com.codegym.a0223i1_pharmacy_professional_be.entity.Symptom;
import com.codegym.a0223i1_pharmacy_professional_be.service.implement.medicinemanagement.MedicineService;
import com.codegym.a0223i1_pharmacy_professional_be.service.implement.prescriptionmanagement.PrescriptionDetailService;
import com.codegym.a0223i1_pharmacy_professional_be.service.implement.prescriptionmanagement.PrescriptionService;
import com.codegym.a0223i1_pharmacy_professional_be.service.implement.prescriptionmanagement.SymptomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("details")
public class PrescriptionDetailController {

    private PrescriptionService prescriptionService;
    private PrescriptionDetailService detailPrescriptionService;
    private MedicineService medicineService;
    private SymptomService symptomService;

    @Autowired
    public PrescriptionDetailController(PrescriptionService prescriptionService, PrescriptionDetailService detailPrescriptionService, MedicineService medicineService, SymptomService symptomService) {
        this.prescriptionService = prescriptionService;
        this.detailPrescriptionService = detailPrescriptionService;
        this.medicineService = medicineService;
        this.symptomService = symptomService;
    }

    @GetMapping
    public ResponseEntity<List<PrescriptionDetail>> findAllPrescription() {
        List<PrescriptionDetail> prescriptionList = detailPrescriptionService.findAll();
        return ResponseEntity.ok(prescriptionList);
    }


    @PostMapping
    public ResponseEntity<PrescriptionDetail> createPrescription(@RequestBody PrescriptionDetailDTO detailPrescriptionDTO) {

        List<PrescriptionDetailDTO> detailPrescriptions = detailPrescriptionDTO.getDetailPrescription();
        List<PrescriptionDetailDTO> saveList = new ArrayList<>();

        PrescriptionDetail detailPrescription = new PrescriptionDetail();

        saveList.addAll(detailPrescriptions);


        detailPrescription.setTimes(saveList.get(0).getTimes() + "," + saveList.get(1).getTimes2() + "," + saveList.get(2).getTimes3() + "," + saveList.get(3).getTimes4()
                + "," + saveList.get(4).getTimes5() + "," + saveList.get(5).getTimes6() + "," + saveList.get(6).getTimes7());

        detailPrescription.setQuantity(saveList.get(0).getQuantity() + "," + saveList.get(1).getQuantity2() + "," + saveList.get(2).getQuantity3() + "," + saveList.get(3).getQuantity4()
                + "," + saveList.get(4).getQuantity5() + "," + saveList.get(5).getQuantity6() + "," + saveList.get(6).getQuantity7());

        detailPrescription.setQuantityPerTimes(saveList.get(0).getQuantityPerTimes() + "," + saveList.get(1).getQuantityPerTimes2() + "," + saveList.get(2).getQuantityPerTimes3()
                + "," + saveList.get(3).getQuantityPerTimes4() + "," + saveList.get(4).getQuantityPerTimes5() + "," + saveList.get(5).getQuantityPerTimes6()
                + "," + saveList.get(6).getQuantityPerTimes7());


        detailPrescriptionDTO.setDeleteFlag(true);

        Symptom symptom = new Symptom();
        symptom.setSymptomName(detailPrescriptionDTO.getPrescription().getSymptom().getSymptomName());

        Prescription prescription = new Prescription();
        prescription.setPrescriptionName(detailPrescriptionDTO.getPrescription().getPrescriptionName());
        prescription.setTarget(detailPrescriptionDTO.getPrescription().getTarget());
        prescription.setTreatmentPeriod(detailPrescriptionDTO.getPrescription().getTreatmentPeriod());
        prescription.setNote(detailPrescriptionDTO.getPrescription().getNote());
        prescription.setDeleteFlag(detailPrescriptionDTO.getDeleteFlag());
        prescription.setPrescriptionId(prescriptionService.generateNextCode());
        prescription.setSymptom(symptom);

        detailPrescription.setPrescription(prescription);


        Medicine medicine = medicineService.findById2(detailPrescriptionDTO.getMedicineId());
        detailPrescription.setMedicine(medicine);

        PrescriptionDetail save = detailPrescriptionService.save(detailPrescription);

        return new ResponseEntity<>(save, HttpStatus.CREATED);
    }



    @PutMapping("/{id}")
    public ResponseEntity<PrescriptionDetail> updateDetailPrescription(@PathVariable Long id, @RequestBody PrescriptionDetailDTO detailPrescriptionDTO) {
        System.out.println(detailPrescriptionDTO.getPrescription().getPrescriptionName());

        List<PrescriptionDetailDTO> detailPrescriptions = detailPrescriptionDTO.getDetailPrescription();
        List<PrescriptionDetailDTO> saveList = new ArrayList<>();


        saveList.addAll(detailPrescriptions);


        PrescriptionDetail existingDetailPrescription = detailPrescriptionService.findById(id);
        Medicine existingMedicine = medicineService.findById2(detailPrescriptionDTO.getMedicineId());
        Prescription existingPrescription = prescriptionService.findPrescriptionById(detailPrescriptionDTO.getPrescription().getPrescriptionId());
        Symptom existingSymptom = symptomService.findSymptomByPrescriptionId(detailPrescriptionDTO.getPrescription().getPrescriptionId());

        if (existingDetailPrescription != null) {
            existingSymptom.setSymptomName(detailPrescriptionDTO.getPrescription().getSymptom().getSymptomName());

            existingDetailPrescription.setQuantityPerTimes(detailPrescriptionDTO.getQuantityPerTimes());
            existingDetailPrescription.setTimes(detailPrescriptionDTO.getTimes());
            existingDetailPrescription.setQuantity(detailPrescriptionDTO.getQuantity());
            existingDetailPrescription.setMedicine(existingMedicine);
            existingDetailPrescription.setMedicine(existingMedicine);

            existingDetailPrescription.setTimes(saveList.get(0).getTimes() + "," + saveList.get(1).getTimes2());
            existingDetailPrescription.setQuantity(saveList.get(0).getQuantity() + "," + saveList.get(1).getQuantity2());
            existingDetailPrescription.setQuantityPerTimes(saveList.get(0).getQuantityPerTimes() + "," + saveList.get(1).getQuantityPerTimes2());

            existingPrescription.setPrescriptionName(detailPrescriptionDTO.getPrescription().getPrescriptionName());
            existingPrescription.setNote(detailPrescriptionDTO.getPrescription().getNote());
            existingPrescription.setTarget(detailPrescriptionDTO.getPrescription().getTarget());
            existingPrescription.setTreatmentPeriod(detailPrescriptionDTO.getPrescription().getTreatmentPeriod());
            existingPrescription.setSymptom(existingSymptom);


            prescriptionService.updatePrescription(existingPrescription, existingSymptom);
            existingDetailPrescription.setPrescription(existingPrescription);

            detailPrescriptionService.updateDetailPrescription(existingDetailPrescription);
            return ResponseEntity.ok(existingDetailPrescription);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<Symptom> findDetailPrescriptionById(@PathVariable Long id) {
        Prescription prescription = prescriptionService.findPrescriptionByDetailId(id);
        PrescriptionDetail detailPrescription = detailPrescriptionService.findById(id);

        if (prescription == null) {
            return ResponseEntity.notFound().build();
        }

        Symptom symptom = symptomService.findSymptomByPrescriptionId(prescription.getPrescriptionId());

        if (symptom == null) {
            return ResponseEntity.notFound().build();
        }

        PrescriptionDetailDTO dto = new PrescriptionDetailDTO();

        dto.setPrescription(prescription);
        dto.setSymptom(symptom);
        dto.setQuantity(detailPrescription.getQuantity());
        dto.setTimes(detailPrescription.getTimes());
        dto.setQuantityPerTimes(detailPrescription.getQuantityPerTimes());
        dto.setMedicineId(detailPrescription.getMedicine().getMedicineId());

        return ResponseEntity.ok(symptom);
    }

}

