package com.codegym.a0223i1_pharmacy_professional_be.controller.symptommanagement;

import com.codegym.a0223i1_pharmacy_professional_be.entity.Symptom;
import com.codegym.a0223i1_pharmacy_professional_be.service.implement.prescriptionmanagement.SymptomService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("symptoms")
public class SymptomController {

    private SymptomService symptomService;

    @Autowired

    public SymptomController(SymptomService symptomService) {
        this.symptomService = symptomService;
    };


    @GetMapping
    public ResponseEntity<List<Symptom>> findAllSymptom() {
        List<Symptom> symptoms = symptomService.findAll();
        return ResponseEntity.ok(symptoms);
    }
    @PostMapping
    public ResponseEntity<Symptom> addSymptom(@Valid @RequestBody Symptom symptom){
        Symptom saveSymptom = symptomService.addSymptom(symptom);
        return new ResponseEntity<>(saveSymptom, HttpStatus.CREATED);
    }

}
