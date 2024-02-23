package com.codegym.a0223i1_pharmacy_professional_be.controller.prescriptionmanagement;

import com.codegym.a0223i1_pharmacy_professional_be.entity.Prescription;
import com.codegym.a0223i1_pharmacy_professional_be.service.implement.prescriptionmanagement.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("prescriptions")
public class PrescriptionController {

    private PrescriptionService prescriptionService;

    @Autowired
    public PrescriptionController(PrescriptionService prescriptionService) {
        this.prescriptionService = prescriptionService;
    }

    @GetMapping
    public ResponseEntity<List<Prescription>> findAllPrescription() {
        List<Prescription> prescriptionList = prescriptionService.findAll();
        return ResponseEntity.ok(prescriptionList);
    }



    @DeleteMapping("/{id}")
    public  ResponseEntity<Void> deletePrescription(@PathVariable String id){
        Prescription existingPrescription = prescriptionService.findPrescriptionById(id);
        if(existingPrescription != null){
            prescriptionService.deletePrescription(id);
            return ResponseEntity.ok().build();
        }else {
            return ResponseEntity.notFound().build();
        }
    }

}

